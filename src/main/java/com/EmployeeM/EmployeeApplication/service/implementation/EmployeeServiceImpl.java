package com.EmployeeM.EmployeeApplication.service.implementation;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import com.EmployeeM.EmployeeApplication.exception.InvalidRequestException;
import com.EmployeeM.EmployeeApplication.exception.NoContentException;
import com.EmployeeM.EmployeeApplication.exception.ResourceNotFoundException;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeService employeeRep;

   @Autowired
    public EmployeeServiceImpl(@Lazy final EmployeeService employeeRep) {
        this.employeeRep = employeeRep;
    }
    @Override
    public Employee addEmployee(Employee employee) throws InvalidRequestException{
        return employeeRep.addEmployee(employee);
    }
    @Override
    public List<Employee> getAllEmployees() throws ResourceNotFoundException {
    return employeeRep.getAllEmployees();
}

@Override
public Employee findEmployeeById(Long id)
        throws ResourceNotFoundException {
       try {
           if (id==null) {
               throw new ResourceNotFoundException("Employee not found with id: " + id);
           }
       }catch (Exception e) {
        log.error(e.getMessage(),e);
       }
    return  employeeRep.findEmployeeById(id);

}

    @Override
    public Employee updateEmployee(Long id, Employee employee) throws ResourceNotFoundException {
        return employeeRep.updateEmployee(id,employee);
    }

    @Override
    public void delete(Long id) throws InvalidRequestException, ResourceNotFoundException {

        employeeRep.delete(id);
    }

}
