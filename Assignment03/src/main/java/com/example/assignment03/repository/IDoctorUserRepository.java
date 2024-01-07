package com.example.assignment03.repository;

import com.example.assignment03.entity.DoctorUser;
import com.example.assignment03.entity.Specializations;
import com.example.assignment03.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IDoctorUserRepository extends JpaRepository<DoctorUser, Integer>, JpaSpecificationExecutor<DoctorUser> {

    List<DoctorUser> findBySpecializationsNameLike(String search);
}
