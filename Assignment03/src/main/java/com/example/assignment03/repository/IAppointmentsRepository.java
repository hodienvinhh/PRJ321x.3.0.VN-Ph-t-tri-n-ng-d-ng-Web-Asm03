package com.example.assignment03.repository;

import com.example.assignment03.entity.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentsRepository extends JpaRepository<Appointments, Integer> {

    List<Appointments> findAllByPatientsId(int id);
}
