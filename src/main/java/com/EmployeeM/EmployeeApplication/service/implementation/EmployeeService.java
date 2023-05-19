package com.EmployeeM.EmployeeApplication.service.implementation;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import com.EmployeeM.EmployeeApplication.exception.InvalidRequestException;
import com.EmployeeM.EmployeeApplication.exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee)throws InvalidRequestException;
    List<Employee> getAllEmployees() throws ResourceNotFoundException;
    Employee findEmployeeById(Long id) throws ResourceNotFoundException;

    Employee updateEmployee(Long id,Employee employee )throws ResourceNotFoundException;
     void delete(Long id)throws InvalidRequestException, ResourceNotFoundException;

}
