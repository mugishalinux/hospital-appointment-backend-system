package com.mugisha.hospital.service.serviceImpl;

import com.mugisha.hospital.entity.Department;
import com.mugisha.hospital.repository.DepartmentRepo;
import com.mugisha.hospital.service.DepartmentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    public DepartmentImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department updateDepartment(Department Department) {
        return null;
    }

    @Override
    public Department findDepartmentById(Integer departmentId) {
        return  departmentRepo.findById(departmentId).get();
    }

    @Override
    public void deleteDepartmentById(Integer DepartmentId) {

    }
}
