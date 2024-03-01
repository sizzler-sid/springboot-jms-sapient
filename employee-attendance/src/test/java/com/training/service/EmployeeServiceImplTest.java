package com.training.service;

import com.training.entity.Employee;
import com.training.repository.EmployeeRepository;
import com.training.service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Doe","IT",null));
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.findAll();

        assertEquals(1, result.size());
    }

    @Test
    public void testFindAllByDate() {
        LocalDate date = LocalDate.now();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Doe","IT",null));
        when(employeeRepository.findAllEmpByDate(date)).thenReturn(employees);

        List<Employee> result = employeeService.findAllByDate(date);

        assertEquals(1, result.size());
    }

    @Test
    public void testFindOne() {
        int id = 1;
        Employee employee = new Employee(1, "John", "Doe","IT",null);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findOne(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindOneNotFound() {
        int id = 1;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        Employee result = employeeService.findOne(id);

        assertNull(result);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee(1, "John", "Doe","IT",null);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
    }

    @Test
    public void testDeleteEmployee() {
        int id = 1;

        assertDoesNotThrow(() -> {
            employeeService.deleteEmployee(id);
        });

        verify(employeeRepository, times(1)).deleteById(id);
    }
}
