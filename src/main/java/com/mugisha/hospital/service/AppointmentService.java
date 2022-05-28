package com.mugisha.hospital.service;

import com.mugisha.hospital.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {
    Appointment createAppointment(Appointment Appointment);
    List<Appointment> getAppointments();
    Appointment updateAppointment(Appointment Appointment);
    Appointment findAppointmentById(Integer AppointmentId);
    void deleteAppointmentById(Integer AppointmentId);

    Appointment findAppointmentByDoctorId(Integer doctorId, LocalDate appointmentDate, Integer appointmentHour);
    List<Appointment> findAppointmentsByDoctorId(Integer doctorId, LocalDate appointmentDate);

    Appointment findAppointmentByAppointmentDate(Integer doctorId,LocalDate appointmentDate);

}
