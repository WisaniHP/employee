package com.EmployeeM.EmployeeApplication.service;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import com.EmployeeM.EmployeeApplication.repository.EmployeeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRep employeeRep;

   @Autowired
    public EmployeeService(EmployeeRep employeeRep) {
        this.employeeRep = employeeRep;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRep.save(employee);
    }

public List<Employee> getAllEmployees() {
    return employeeRep.findAll();
}

    public Employee findEmployeeById(Long id) {
        return employeeRep.findById(id).orElseThrow(() -> new IndexOutOfBoundsException("Employee with id " + id + " not found!"));
    }

    public void delete(Long id) {

        employeeRep.deleteById(id);
    }
}
