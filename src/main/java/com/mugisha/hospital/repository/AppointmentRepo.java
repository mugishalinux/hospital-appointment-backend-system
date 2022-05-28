package com.mugisha.hospital.repository;

import com.mugisha.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    @Query("FROM Appointment WHERE doctor.id=?1 AND appointmentDate=?2 AND appointmentHour=?3")
    Appointment findDoctorAppointment(Integer doctorId, LocalDate appointmentDate, Integer appointmentHour);

    @Query("FROM Appointment WHERE doctor.id=?1 AND appointmentDate=?2 ")
    List<Appointment> findDoctorAppointmentHours(Integer doctorId,LocalDate appointmentDate);

    @Query("FROM Appointment WHERE doctor.id=?1 AND appointmentDate=?2 ")
    Appointment findAppointmentByAppointmentDate(Integer doctorId,LocalDate date);
}
