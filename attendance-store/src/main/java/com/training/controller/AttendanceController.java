package com.training.controller;

import com.training.dto.EmpAttendanceResponseDTO;
import com.training.service.AttendanceService;
import com.training.utility.AttendanceObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.EmpAttendance;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/attendance/")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceServiceImpl;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmpAttendanceResponseDTO>> getAttendanceByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<EmpAttendance> attendances = attendanceServiceImpl.getAttendanceByDate(date);
        if (attendances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(AttendanceObjectMapper.attendanceListToAttendanceDTO(attendances));
        }
    }

    @GetMapping("/fromDate/{fromDate}/toDate/{toDate}")
    public ResponseEntity<List<EmpAttendanceResponseDTO>> getAttendanceByDateRange(
            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        List<EmpAttendance> attendances = attendanceServiceImpl.getAttendanceByDateRange(fromDate, toDate);
        if (attendances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(AttendanceObjectMapper.attendanceListToAttendanceDTO(attendances));
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<EmpAttendanceResponseDTO>> getAttendanceByDate(@PathVariable String name) {
        List<EmpAttendance> attendances = attendanceServiceImpl.getAttendanceByName(name);
        if (attendances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(AttendanceObjectMapper.attendanceListToAttendanceDTO(attendances));
        }
    }
}
