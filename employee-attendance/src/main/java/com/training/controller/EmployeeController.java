package com.training.controller;

import java.time.LocalDate;
import java.util.List;

import com.training.dto.EmployeeRequestDTO;
import com.training.dto.EmployeeResponseDTO;
import com.training.entity.Employee;
import com.training.service.Impl.EmployeeServiceImpl;
import com.training.service.Impl.KafkaProducerServiceImpl;
import com.training.utility.EmployeeObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;
    private KafkaProducerServiceImpl kafkaProducerService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, KafkaProducerServiceImpl kafkaProducerService) {
        this.employeeService = employeeService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> retrieveAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(EmployeeObjectMapper.empListToEmpListDTO(employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> retrieveEmployee(@PathVariable int id) {
        Employee employee = employeeService.findOne(id);
        if (employee != null) {
            return ResponseEntity.ok(EmployeeObjectMapper.empToEmpDTO(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) {
        Employee savedEmployee = employeeService.createEmployee(EmployeeObjectMapper.empDTOToEmp(employeeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeObjectMapper.empToEmpDTO(savedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/kafka/attendance/{date}")
    public ResponseEntity<String> sendKafkaMessage(@PathVariable LocalDate date) {
        kafkaProducerService.deriveAttendance(date);
        return ResponseEntity.ok("Kafka message sent successfully");
    }
}
