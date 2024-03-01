package com.training.service;

import com.training.entity.Employee;
import com.training.entity.Swipe;
import com.training.repository.SwipeRepository;
import com.training.service.Impl.EmployeeServiceImpl;
import com.training.service.Impl.SwipeServiceImpl;
import com.training.utility.SwipeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SwipeServiceImplTest {

    @Mock
    private SwipeRepository swipeRepository;

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private SwipeServiceImpl swipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSwipeInRecord() {
        int empId = 1;
        Employee employee = new Employee();
        Swipe swipe = new Swipe();
        swipe.setSwipeType(SwipeType.SWIPE_IN.toString());
        when(employeeService.findOne(empId)).thenReturn(employee);
        when(swipeRepository.save(any())).thenReturn(swipe);

        Swipe result = swipeService.createSwipeInRecord(empId);

        assertNotNull(result);
        assertEquals(SwipeType.SWIPE_IN.toString(), result.getSwipeType());
        assertEquals(swipe, result);
    }

    @Test
    public void testCreateSwipeOutRecord() {
        int empId = 1;
        Employee employee = new Employee();
        Swipe swipe = new Swipe();
        swipe.setSwipeType(SwipeType.SWIPE_OUT.toString());
        when(employeeService.findOne(empId)).thenReturn(employee);
        when(swipeRepository.save(any())).thenReturn(swipe);

        Swipe result = swipeService.createSwipeOutRecord(empId);

        assertNotNull(result);
        assertEquals(SwipeType.SWIPE_OUT.toString(), result.getSwipeType());
        assertEquals(swipe, result);
    }

    @Test
    public void testCreateCustomSwipeRecord() {
        int empId = 1;
        Employee employee = new Employee();
        Swipe swipe = new Swipe();
        LocalDateTime dateTime = LocalDateTime.now();
        swipe.setDateTime(dateTime);

        when(employeeService.findOne(empId)).thenReturn(employee);
        when(swipeRepository.save(swipe)).thenReturn(swipe);

        Swipe result = swipeService.createCustomSwipeRecord(swipe, empId);

        assertNotNull(result);
        assertEquals(employee, result.getEmp());
        assertEquals(dateTime.toLocalDate(), result.getDate());
    }

    @Test
    public void testDeleteSwipeRecord() {
        int id = 1;
        doNothing().when(swipeRepository).deleteById(id);

        assertDoesNotThrow(() -> {
            swipeService.deleteSwipeRecord(id);
        });

        verify(swipeRepository, times(1)).deleteById(id);
    }
}
