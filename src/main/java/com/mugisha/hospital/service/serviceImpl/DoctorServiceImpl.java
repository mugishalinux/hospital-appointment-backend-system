package com.mugisha.hospital.service.serviceImpl;

import com.mugisha.hospital.entity.Doctor;
import com.mugisha.hospital.repository.DoctorRepo;
import com.mugisha.hospital.service.DoctorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor findDoctorById(Integer doctorId) {
        return doctorRepo.findById(doctorId).get();
    }

    @Override
    public void deleteDoctorById(Integer doctorId) {

    }

    @Override
    public Doctor findDoctorByEmail(String email) {
        return null;
    }
}
