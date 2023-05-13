package com.EmployeeM.EmployeeApplication.Controller;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import com.EmployeeM.EmployeeApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeservice;


    @Autowired
    public  EmployeeController(EmployeeService employeeservice){
        this.employeeservice = employeeservice;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
       public List<Employee> getAllemployees() {
           return employeeservice.getAllEmployees();
        }


    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable("id") Long id) {
     return employeeservice.findEmployeeById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
         return employeeservice.addEmployee(employee);
    }

@PutMapping("/update/{id}")
@ResponseStatus(HttpStatus.OK)
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
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
        return ResponseEntity.notFound().build();
    }
}

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id)
    {
        employeeservice.delete(id);

    }

}
