package com.mugisha.hospital.service;

import com.mugisha.hospital.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department createDepartment(Department Department);
    List<Department> getDepartments();
    Department updateDepartment(Department Department);
    Department findDepartmentById(Integer DepartmentId);
    void deleteDepartmentById(Integer DepartmentId);
    
}
