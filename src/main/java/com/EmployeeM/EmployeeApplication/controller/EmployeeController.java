package com.EmployeeM.EmployeeApplication.controller;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import com.EmployeeM.EmployeeApplication.exception.InvalidRequestException;
import com.EmployeeM.EmployeeApplication.exception.NoContentException;
import com.EmployeeM.EmployeeApplication.exception.ResourceExistException;
import com.EmployeeM.EmployeeApplication.exception.ResourceNotFoundException;
import com.EmployeeM.EmployeeApplication.service.implementation.EmployeeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeservice;


    @Autowired
    public  EmployeeController(EmployeeServiceImpl employeeservice){
        this.employeeservice = employeeservice;
    }

    @GetMapping
    @ResponseBody
       public List<Employee> getAllemployees()
            throws ResourceNotFoundException {
           return employeeservice.getAllEmployees();
        }


    @GetMapping("/find/{id}")
    @ResponseBody
    public Employee findEmployeeById(@PathVariable("id") Long id)
            throws ResourceNotFoundException {
        try{
            log.info("Get Employee with id {} from database",id);
            return employeeservice.findEmployeeById(id);
        }
        catch (Exception e){
            log.info("Employee linked with id:"+ id + " not found");
          throw e;
        }

    }

    @PostMapping("/add")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) throws InvalidRequestException{
        return employeeservice.addEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) throws ResourceNotFoundException, InvalidRequestException {
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeservice.findEmployeeById(id));
        if (optionalEmployee.isPresent()) {
            Employee updateEmployee = optionalEmployee.get();
            updateEmployee.setName(employee.getName());
            updateEmployee.setAge(employee.getAge());
            updateEmployee.setSurname(employee.getSurname());
            updateEmployee.setEmployeeNumber(employee.getEmployeeNumber());
            updateEmployee.setSalary(employee.getSalary());
            employeeservice.addEmployee(updateEmployee);
            return ResponseEntity.ok(updateEmployee);
        } else {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws  InvalidRequestException,ResourceNotFoundException{
        employeeservice.delete(id);

    }

}
