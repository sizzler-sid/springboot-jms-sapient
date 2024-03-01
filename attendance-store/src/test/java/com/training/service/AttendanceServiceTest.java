package com.training.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.training.entity.EmpAttendance;
import com.training.exception.CustomException;
import com.training.repository.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private Logger logger;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAttendanceByDate() {
        LocalDate date = LocalDate.now();
        List<EmpAttendance> mockAttendanceList = new ArrayList<>();
        mockAttendanceList.add(new EmpAttendance());

        when(attendanceRepository.findByDate(eq(date), eq(date))).thenReturn(mockAttendanceList);

        List<EmpAttendance> result = attendanceService.getAttendanceByDate(date);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetAttendanceByDateWhenNoDataFound() {
        LocalDate date = LocalDate.now();

        when(attendanceRepository.findByDate(eq(date), eq(date))).thenThrow(Mockito.mock(DataAccessException.class));

        assertThrows(CustomException.class, () -> attendanceService.getAttendanceByDate(date));
    }

    @Test
    public void testGetAttendanceByDateRange() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        List<EmpAttendance> mockAttendanceList = new ArrayList<>();
        mockAttendanceList.add(new EmpAttendance());
        mockAttendanceList.add(new EmpAttendance());

        when(attendanceRepository.findByDate(eq(fromDate), eq(toDate))).thenReturn(mockAttendanceList);

        List<EmpAttendance> result = attendanceService.getAttendanceByDateRange(fromDate, toDate);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAttendanceByDateRangeWhenNoDataFound() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();

        when(attendanceRepository.findByDate(eq(fromDate), eq(toDate))).thenThrow(Mockito.mock(DataAccessException.class));

        assertThrows(CustomException.class, () -> attendanceService.getAttendanceByDateRange(fromDate, toDate));
    }

    @Test
    public void testGetAttendanceByName() {
        String name = "John Doe";
        List<EmpAttendance> mockAttendanceList = new ArrayList<>();
        mockAttendanceList.add(new EmpAttendance());

        when(attendanceRepository.findByName(eq(name))).thenReturn(mockAttendanceList);

        List<EmpAttendance> result = attendanceService.getAttendanceByName(name);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetAttendanceByNameWhenNoDataFound() {
        String name = "John Doe";

        when(attendanceRepository.findByName(eq(name))).thenThrow(Mockito.mock(DataAccessException.class));
        assertThrows(CustomException.class, () -> attendanceService.getAttendanceByName(name));
    }

}
