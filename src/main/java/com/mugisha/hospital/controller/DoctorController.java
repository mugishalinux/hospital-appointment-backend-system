package com.mugisha.hospital.controller;


import com.mugisha.hospital.entity.Department;
import com.mugisha.hospital.entity.Doctor;
import com.mugisha.hospital.exception.DbConstraintException;
import com.mugisha.hospital.exception.DoctorExistException;
import com.mugisha.hospital.exception.DoctorInputException;
import com.mugisha.hospital.exception.DoctorNotFoundException;
import com.mugisha.hospital.service.DepartmentService;
import com.mugisha.hospital.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/doctor")
@Api(value = "Doctor Endpoints")
public class DoctorController {
    private static Map<String,String> errors;
    private final DoctorService doctorService;

    private final DepartmentService departmentService;

    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @ApiOperation(value="Returns List of Employees Details")
    @GetMapping("")
    public ResponseEntity<List<Doctor>> getAllDoctors() throws DoctorNotFoundException {
        List<Doctor>doctors=doctorService.getDoctors();
        if(doctors.isEmpty())throw new DoctorNotFoundException("List is Empty");

        return new ResponseEntity<>(doctors,HttpStatus.OK);
    }

    @ApiOperation(value = "register a doctor and returns a confirmation Message to that registered user")
    @PostMapping("/register/dept/{id}")
    public ResponseEntity<Map<String,String>> registerDoctor(@RequestBody Doctor doctor ,@PathVariable Integer id) throws DoctorInputException, DoctorExistException, DbConstraintException, DoctorNotFoundException {
        Map<String,String> map=new HashMap<>();
        //find the department by id

        Department department=departmentService.findDepartmentById(id);
        if (department==null) throw new DoctorNotFoundException(" department not found");
        //set Departmen on newly creatd doctor
        if(doctor==null)throw new DoctorInputException("Fields must be filled");

        if(checkDoctorInfo(doctor));

        Doctor existingDoctor=doctorService.findDoctorByEmail(doctor.getEmail());

        if(existingDoctor!=null)throw new DoctorExistException("doctor is Already Registered");


        if(ConstraintViolationException.class==null) throw new DbConstraintException("Constraint Violation Exception");
        doctor.setDepartment(department);
        Doctor newDoctor=doctorService.createDoctor(doctor);

        map.put("message", "doctor created");

        return new ResponseEntity<Map<String,String>>(map, HttpStatus.CREATED);

    }

    private boolean checkDoctorInfo(Doctor doctor) throws DoctorInputException {

        errors=new HashMap<>();
        if(doctor.getFirstName().trim().isEmpty()) {
            errors.put("firstName", "fistName can not be Empty");
            throw new DoctorInputException(errors.get("firstName"));

        }

        if(doctor.getEmail().isEmpty()) {
            errors.put("email", "Email must be filled");
            throw new DoctorInputException(errors.get("email"));
        }

        if(errors.isEmpty()) {
            return true;
        }
        throw new DoctorInputException("one or more fields contains an error");


    }


}
