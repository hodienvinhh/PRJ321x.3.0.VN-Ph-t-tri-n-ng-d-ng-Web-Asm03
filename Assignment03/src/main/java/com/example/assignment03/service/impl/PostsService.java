package com.example.assignment03.service.impl;

import com.example.assignment03.dto.PostsDTO;
import com.example.assignment03.entity.*;
import com.example.assignment03.exception.BasicException;
import com.example.assignment03.form.CreatePostForm;
import com.example.assignment03.repository.*;
import com.example.assignment03.service.IPostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostsService implements IPostsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPostsRepository postsRepository;

    @Autowired
    private IPatientsRepository patientsRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISpecializationsRepository specializationsRepository;

    @Autowired
    private IClinicsRepository clinicsRepository;


    @Override
    public void createPost(CreatePostForm form) throws ParseException {

        Posts posts = new Posts();
        // Set giá trị cho Posts
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        posts.setDateTimeBooking(simpleDateFormat.parse(String.valueOf(form.getDateTimeBooking())));
        posts.setTitle(form.getTitle());
        //Tìm Doctor ID rồi set vào Bảng Posts
        Optional<User> optionalUser = userRepository.findById(form.getDoctorId());
        if (!optionalUser.isPresent()){
            throw  new RuntimeException("Not Found User");
        }
        posts.setDoctorId(optionalUser.get());
        //Tìm Specializations ID rồi set vào Bảng Posts
        Optional<Specializations> specializationOptional = specializationsRepository.findById(form.getSpecializationsId());
        if (!optionalUser.isPresent()){
            throw  new RuntimeException("Not Found Specializations");
        }
        posts.setSpecializations(specializationOptional.get());

        //Tìm Clinics ID rồi set vào Bảng Posts
        Optional<Clinics> optionalClinics = clinicsRepository.findById(form.getClinicsId());
        if (!optionalUser.isPresent()){
            throw  new RuntimeException("Not Found Clinics");
        }
        posts.setClinics(optionalClinics.get());
        posts.setCreateAt(new Date());
        // Set giá trị cho Patients nếu bệnh nhân chưa tồn tại.
        Patients patients = new Patients();
        patients.setName(form.getNamePatient());
        patients.setGender(form.getGender());
        patients.setPhone(form.getPhone());
        patients.setAddress(form.getAddress());
        patients.setPathological(form.getPathological());
        patients.setDoctor(optionalUser.get());
        patientsRepository.save(patients);

        posts.setPatients(patients);
        postsRepository.save(posts);
    }


    @Override
    public void changeConfirmByDoctor(int id, int status, String content) {
        Optional<Posts> postsOptional = Optional.ofNullable(postsRepository.findById(id));
        Posts posts = null;
        if (postsOptional.isPresent()){
          posts = postsOptional.get();
        }
        if (posts.getConfirmByDoctor()!=0)
            throw BasicException.INVALID_ARGUMENT.addError("Yêu cầu đã được xác nhận!");
        posts.setConfirmByDoctor(status);
        if (status == 2 && StringUtils.isEmpty(content))
            throw BasicException.INVALID_ARGUMENT.addError("Vui lòng nhập lý do!");
        posts.setContentHtml(content);
        // Lưu lại
        postsRepository.save(posts);
    }

    @Override
    public PostsDTO viewAppointmentsUserByAdmin(int id){
        Optional<Patients> patientsOptional = patientsRepository.findById(id);
        if (!patientsOptional.isPresent()){
            throw BasicException.NOT_FOUND.addError("Không tìm thấy ID :" + id);
//            throw  new RuntimeException("Not Found ID :" + id);
        }
        PostsDTO.PatientDTO patientsDTO = new PostsDTO.PatientDTO(patientsOptional.get());
       List<Posts> posts =  postsRepository.findAllByPatientsId(patientsOptional.get().getId());
        if (posts.isEmpty()){
            throw BasicException.NOT_FOUND.addError("Không tìm thấy bài Post");
        }
        List<PostsDTO.PostDTO> postDTOS = new ArrayList<>();
        for (Posts app : posts){
            PostsDTO.PostDTO postDTO = new PostsDTO.PostDTO(app);
            postDTOS.add(postDTO);
        }
        PostsDTO postsDTO = new PostsDTO(patientsDTO, postDTOS);
        return postsDTO;
    }

}
