package com.training.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.training.entity.Employee;
import com.training.exception.CustomException;
import com.training.repository.EmployeeRepository;
import com.training.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        try {
            logger.info("Finding all employees.");
            return employeeRepository.findAll();
        } catch (Exception e) {
            logger.error("An error occurred while finding all employees.", e);
            throw new CustomException("Failed to retrieve employees.", e);
        }
    }

    public List<Employee> findAllByDate(LocalDate date) {
        try {
            logger.info("Finding all employees By Date.");
            return employeeRepository.findAllEmpByDate(date);
        } catch (Exception e) {
            logger.error("An error occurred while finding all employees by Date.", e);
            throw new CustomException("Failed to retrieve employees.", e);
        }
    }

    public Employee findOne(int id) {
        try {
            logger.info("Finding employee with ID: {}", id);
            Optional<Employee> emp = employeeRepository.findById(id);
            return emp.orElse(null);
        } catch (Exception e) {
            logger.error("An error occurred while finding an employee with ID: {}", id, e);
            throw new CustomException("Failed to retrieve employee with ID: " + id, e);
        }
    }

    public Employee createEmployee(Employee employee) {
        try {
            logger.info("Creating a new employee: {}", employee);
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("An error occurred while creating an employee.", e);
            throw new CustomException("Failed to create employee.", e);
        }
    }

    public void deleteEmployee(int id) {
        try {
            logger.info("Deleting employee with ID: {}", id);
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while deleting an employee with ID: {}", id, e);
            throw new CustomException("Failed to delete employee with ID: " + id, e);
        }
    }
}
