package com.example.assignment03.repository;

import com.example.assignment03.dto.IClinicsDTO;
import com.example.assignment03.dto.ISpecializationsDTO;
import com.example.assignment03.entity.Clinics;
import com.example.assignment03.entity.Specializations;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClinicsRepository extends JpaRepository<Clinics, Integer>, JpaSpecificationExecutor<Clinics> {
    @Query(value = "   Select count(FOR_CLINIC_ID) as Max_search, c.`NAME`, c.ADDRESS, c.COST_OF_EXAMINATION, c.PHONE from Posts p\n" +
            " join Clinics c\n" +
            " on c.id = p.FOR_CLINIC_ID\n" +
            " group by p.FOR_CLINIC_ID\n" +
            " order by count(FOR_CLINIC_ID) desc limit 3;", nativeQuery = true)
    List<IClinicsDTO> getTopClinics();





}
