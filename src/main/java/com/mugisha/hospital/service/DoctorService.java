package com.mugisha.hospital.service;

import com.mugisha.hospital.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

     Doctor createDoctor(Doctor doctor);
     List<Doctor> getDoctors();
     Doctor updateDoctor(Doctor doctor);
     Doctor findDoctorById(Integer doctorId);
     void deleteDoctorById(Integer doctorId);
     Doctor findDoctorByEmail(String email);

}
