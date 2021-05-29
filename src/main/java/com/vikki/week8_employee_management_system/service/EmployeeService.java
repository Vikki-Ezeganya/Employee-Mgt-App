package com.vikki.week8_employee_management_system.service;

import com.vikki.week8_employee_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService{
    List<Employee>getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Employee getEmployeeByEmail(String email);
}
