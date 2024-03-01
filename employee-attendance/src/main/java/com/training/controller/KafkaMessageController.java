package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.service.Impl.KafkaProducerServiceImpl;

import java.time.LocalDate;

@RestController
public class KafkaMessageController
{

    @Autowired
    private KafkaProducerServiceImpl kafkaProducerServiceImpl;

    @PostMapping("/kafka/attendance/{date}")
    public ResponseEntity<String> sendKafkaMessage(@PathVariable LocalDate date) {
        //List<Employee> empList = employeeService.findAll();
        kafkaProducerServiceImpl.deriveAttendance(date);
        return ResponseEntity.ok("Kafka message sent successfully");
    }
}
