package com.example.assignment03.repository;

import com.example.assignment03.dto.IDoctorUserDTO;
import com.example.assignment03.dto.ISpecializationsDTO;
import com.example.assignment03.entity.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISpecializationsRepository extends JpaRepository<Specializations, Integer> , JpaSpecificationExecutor<Specializations> {

    @Query(value = "Select count(FOR_SPECIALIZATION_ID) as Max_search, s.`NAME`,s.DESCRIPTION from Posts p\n" +
            "join Specializations s\n" +
            "on s.id = p.FOR_SPECIALIZATION_ID\n" +
            "group by p.FOR_SPECIALIZATION_ID\n" +
            "order by count(FOR_SPECIALIZATION_ID) desc limit 3;", nativeQuery = true)
    List<ISpecializationsDTO> getSpecializations();


    @Query(value = "select u.name as doctor_name, c.name as clinics_name, s.name as specializations_name from  Doctor_User du\n" +
            "join Specializations s on du.SPECIALIZATION_ID = s.id\n" +
            "join `Users` u on du.DOCTOR_ID = u.id\n" +
            "join Clinics c on du.CLINIC_ID = c.id\n" +
            "where s.`NAME` like concat('%',:search, '%');",nativeQuery = true)
    List<IDoctorUserDTO> searchBySpecializationsName(String search);


}
