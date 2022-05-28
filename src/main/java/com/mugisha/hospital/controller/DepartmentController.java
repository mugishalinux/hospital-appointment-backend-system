package com.mugisha.hospital.controller;


import com.mugisha.hospital.entity.Department;
import com.mugisha.hospital.entity.Doctor;
import com.mugisha.hospital.exception.DbConstraintException;
import com.mugisha.hospital.exception.DoctorExistException;
import com.mugisha.hospital.exception.DoctorInputException;
import com.mugisha.hospital.exception.DoctorNotFoundException;
import com.mugisha.hospital.service.DepartmentService;
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
@RequestMapping("/api/v1/department")
@Api(value = "Department Endpoints")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value="Returns List of Departments Details")
    @GetMapping("")
    public ResponseEntity<List<Department>> getAllDepartments() throws DoctorNotFoundException {
        List<Department>departments=departmentService.getDepartments();
        if(departments.isEmpty())throw new DoctorNotFoundException("List is Empty");

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @ApiOperation(value = "register a department and returns a confirmation Message ")
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerDepartment(@RequestBody Department department ) {
        Map<String,String> map=new HashMap<>();

        departmentService.createDepartment(department);
                map.put("message","department created");

        return new ResponseEntity<Map<String,String>>(map, HttpStatus.CREATED);

    }
}
