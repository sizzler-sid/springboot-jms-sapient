package com.training.service;

import com.training.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public List<Employee> findAllByDate(LocalDate date);
    public Employee findOne(int id);
    public Employee createEmployee(Employee employee);
    public void deleteEmployee(int id);

}
