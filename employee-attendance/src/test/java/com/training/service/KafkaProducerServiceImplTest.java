package com.training.service;

import com.training.entity.Employee;
import com.training.entity.Swipe;
import com.training.model.Attendance;
import com.training.service.Impl.EmployeeServiceImpl;
import com.training.service.Impl.KafkaProducerServiceImpl;
import com.training.utility.SwipeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private KafkaTemplate<String, Attendance> kafkaTemplate;

    @InjectMocks
    private KafkaProducerServiceImpl kafkaProducerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeriveAttendance() {
        LocalDate date = LocalDate.now();
        Employee employee = new Employee();
        Swipe swipeIn = new Swipe();
        swipeIn.setSwipeType(SwipeType.SWIPE_IN.toString());
        swipeIn.setDateTime(LocalDateTime.now().minusHours(5));
        Swipe swipeOut = new Swipe();
        swipeOut.setSwipeType(SwipeType.SWIPE_OUT.toString());
        swipeOut.setDateTime(LocalDateTime.now());
        List<Swipe> swipes = new ArrayList<>();
        swipes.add(swipeIn);
        swipes.add(swipeOut);
        employee.setSwipe(swipes);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(employeeService.findAllByDate(date)).thenReturn(employees);

        kafkaProducerService.deriveAttendance(date);

        verify(kafkaTemplate, times(1)).send(anyString(), any(Attendance.class));
    }
}
