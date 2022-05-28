package com.mugisha.hospital.service.serviceImpl;

import com.mugisha.hospital.entity.Appointment;
import com.mugisha.hospital.repository.AppointmentRepo;
import com.mugisha.hospital.service.AppointmentService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AppointmentImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;

    public AppointmentImpl(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(Integer appointmentId) {
        return appointmentRepo.findById(appointmentId).get();
    }

    @Override
    public void deleteAppointmentById(Integer appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }

    @Override
    public Appointment findAppointmentByDoctorId(Integer doctorId, LocalDate appointmentDate, Integer appointmentHour) {
        return appointmentRepo.findDoctorAppointment(doctorId,appointmentDate,appointmentHour);
    }

    @Override
    public List<Appointment> findAppointmentsByDoctorId(Integer doctorId, LocalDate appointmentDate) {
        return appointmentRepo.findDoctorAppointmentHours(doctorId,appointmentDate);
    }

    @Override
    public Appointment findAppointmentByAppointmentDate(Integer doctorId, LocalDate appointmentDate) {
        return appointmentRepo.findAppointmentByAppointmentDate(doctorId,appointmentDate);
    }
}
