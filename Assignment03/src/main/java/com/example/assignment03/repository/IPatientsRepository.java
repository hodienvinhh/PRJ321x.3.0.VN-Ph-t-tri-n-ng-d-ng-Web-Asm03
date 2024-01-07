package com.example.assignment03.repository;

import com.example.assignment03.entity.Patients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientsRepository extends JpaRepository<Patients, Integer> {
    Page<Patients> findPatientsByDoctorId(Pageable pageable, int userId);


    List<Patients> findAllByDoctorId(int id);

}
