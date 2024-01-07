package com.example.assignment03.service.impl;

import com.example.assignment03.entity.User;
import com.example.assignment03.repository.IPatientsRepository;
import com.example.assignment03.repository.IUserRepository;
import com.example.assignment03.service.IEmailService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPatientsRepository patientsRepository;


    public final  String SEND_EMAIL = System.getProperty("user.dir") + "/src/main/resources/file/";



    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void forgotPassword(String to) {
        // tao token+ luu token vao db
        String token = UUID.randomUUID().toString();
//        userRepository.findByToken
        //neu ton tai => tao lai token
        User user = userRepository.findByEmail(to);
        if (Objects.isNull(user)) {
            throw new RuntimeException("Email không tồn tại");
        }
        user.setToken(token);
        userRepository.save(user);
        //gui email
        String body = "Click vào đây để thay đổi MK: ";
        String link = "http://localhost:8080/api/v1/users/changePassword?token=" + token;
        sendEmail(to, "Forgot password", body + link);
    }




    @Override
    public void confirmByDoctor(String to) {
        // tao token+ luu token vao db
        String token = UUID.randomUUID().toString();
//        userRepository.findByToken
        //neu ton tai => tao lai token
        User user = userRepository.findByEmail(to);
        if (Objects.isNull(user))
            throw new RuntimeException("");
        user.setToken(token);
        userRepository.save(user);
        //gui email
        String body = "Xác nhận khám chữa bệnh :";
        String link = "http://localhost:8080/api/v1/users/confirmByDoctor?token=" + token;
        sendEmail(to, "Xác nhận khám bệnh .", body + link);
    }

    @Override
    public void sendMessageWithAttachment(String to,
                                          String subject,
                                          String text,
                                          MultipartFile file1
                                          ) throws MessagingException, IOException, DocumentException {
        // Gui email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

//        // Tạo File PDF
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");//abc.jpg
//        Path fileNameAndPath = Paths.get(SEND_EMAIL, simpleDateFormat.format(new Date())+ ".pdf");
//
//        Document document = new Document();//01042024215100.pdf
//        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(fileNameAndPath)));// Lấy link của thư mục chưa File
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK.BLACK);
//        Chunk chunk = new Chunk("Thông tin bệnh lý của bệnh nhân", font);
//
//        document.add(chunk);
//        document.close();

        // Thêm tệp đính kèm vào Email

//        FileSystemResource file
//          //      = new FileSystemResource(new File(SEND_EMAIL, simpleDateFormat.format(new Date())+ ".pdf"));// Link lay den phan sour + ten file
//                = new FileSystemResource(file1.);// Link lay den phan sour + ten file
        helper.addAttachment(file1.getOriginalFilename(), file1);// doi lai ten cho file khi nguoi dung nhan email

        try {
            mailSender.send(message);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
