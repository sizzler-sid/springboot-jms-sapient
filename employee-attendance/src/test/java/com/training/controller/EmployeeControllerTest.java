package com.training.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.training.dto.EmployeeRequestDTO;
import com.training.dto.EmployeeResponseDTO;
import com.training.entity.Employee;
import com.training.service.Impl.EmployeeServiceImpl;
import com.training.service.Impl.KafkaProducerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private KafkaProducerServiceImpl kafkaProducerService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        when(employeeService.findAll()).thenReturn(employees);

        ResponseEntity<List<EmployeeResponseDTO>> response = employeeController.retrieveAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }


    @Test
    public void testRetrieveEmployee() {
        Employee employee = new Employee(1, "John", "Doe", "IT",null);
        when(employeeService.findOne(1)).thenReturn(employee);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.retrieveEmployee(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    public void testRetrieveEmployeeNotFound() {
        when(employeeService.findOne(1)).thenReturn(null);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.retrieveEmployee(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateEmployee() {
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("John", "Doe","IT");
        Employee savedEmployee = new Employee(1, "John", "Doe","IT",null);
        when(employeeService.createEmployee(any())).thenReturn(savedEmployee);

        ResponseEntity<EmployeeResponseDTO> response = employeeController.createEmployee(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    public void testDeleteEmployee() {
        ResponseEntity<Void> response = employeeController.deleteEmployee(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    public void testSendKafkaMessage() {
        LocalDate date = LocalDate.now();
        ResponseEntity<String> response = employeeController.sendKafkaMessage(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(kafkaProducerService, times(1)).deriveAttendance(date);
    }

}
