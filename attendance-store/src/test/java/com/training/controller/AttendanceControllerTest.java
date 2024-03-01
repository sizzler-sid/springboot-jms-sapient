package com.training.controller;

import com.training.dto.EmpAttendanceResponseDTO;
import com.training.entity.EmpAttendance;
import com.training.model.Attendance;
import com.training.service.AttendanceService;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAttendanceByDate() {
        LocalDate date = LocalDate.now();
        List<EmpAttendance> attendanceList = new ArrayList<>();
        attendanceList.add(new EmpAttendance()); // Add sample attendance for testing

        when(attendanceService.getAttendanceByDate(date)).thenReturn(attendanceList);

        ResponseEntity<List<EmpAttendanceResponseDTO>> responseEntity = attendanceController.getAttendanceByDate(date);

        verify(attendanceService, times(1)).getAttendanceByDate(date);
        assertAll(() -> {
            Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Assertions.assertNotNull(responseEntity.getBody());
            Assertions.assertEquals(1, responseEntity.getBody().size());
        });
    }

    @Test
    public void testGetAttendanceByDateRange() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        List<EmpAttendance> attendanceList = new ArrayList<>();
        attendanceList.add(new EmpAttendance()); // Add sample attendance for testing

        when(attendanceService.getAttendanceByDateRange(fromDate, toDate)).thenReturn(attendanceList);

        ResponseEntity<List<EmpAttendanceResponseDTO>> responseEntity = attendanceController.getAttendanceByDateRange(fromDate, toDate);

        verify(attendanceService, times(1)).getAttendanceByDateRange(fromDate, toDate);
        assertAll(() -> {
            Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Assertions.assertNotNull(responseEntity.getBody());
            Assertions.assertEquals(1, responseEntity.getBody().size());
        });
    }

    @Test
    public void testGetAttendanceByName() {
        String name = "John Doe";
        List<EmpAttendance> attendanceList = new ArrayList<>();
        attendanceList.add(new EmpAttendance()); // Add sample attendance for testing

        when(attendanceService.getAttendanceByName(name)).thenReturn(attendanceList);

        ResponseEntity<List<EmpAttendanceResponseDTO>> responseEntity = attendanceController.getAttendanceByDate(name);

        verify(attendanceService, times(1)).getAttendanceByName(name);
        assertAll(() -> {
            Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Assertions.assertNotNull(responseEntity.getBody());
            Assertions.assertEquals(1, responseEntity.getBody().size());
        });
    }
}
