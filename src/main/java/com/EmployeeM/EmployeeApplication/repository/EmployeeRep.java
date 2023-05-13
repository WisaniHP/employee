package com.EmployeeM.EmployeeApplication.repository;

import com.EmployeeM.EmployeeApplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRep extends JpaRepository<Employee, Long> {
}
