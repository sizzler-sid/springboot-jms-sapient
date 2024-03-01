package com.training.controller;

import com.training.dto.SwipeRequestDTO;
import com.training.dto.SwipeResponseDTO;
import com.training.entity.Swipe;
import com.training.service.Impl.SwipeServiceImpl;
import com.training.utility.SwipeObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SwipeControllerTest {

    @Mock
    private SwipeServiceImpl swipeService;

    @InjectMocks
    private SwipeController swipeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSwipeInRecord() {
        int empId = 123;
        Swipe swipeInRecord = new Swipe();
        when(swipeService.createSwipeInRecord(empId)).thenReturn(swipeInRecord);

        ResponseEntity<SwipeResponseDTO> response = swipeController.createSwipeInRecord(empId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(swipeInRecord, SwipeObjectMapper.swipeDTOToSwipe(response.getBody()));
    }

    @Test
    public void testCreateSwipeOutRecord() {
        int empId = 123;
        Swipe swipeOutRecord = new Swipe();
        when(swipeService.createSwipeOutRecord(empId)).thenReturn(swipeOutRecord);

        ResponseEntity<SwipeResponseDTO> response = swipeController.createSwipeOutRecord(empId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(swipeOutRecord, SwipeObjectMapper.swipeDTOToSwipe(response.getBody()));
    }

    @Test
    public void testCreateCustomSwipeRecord() {
        int empId = 123;
        SwipeRequestDTO swipeDTO = new SwipeRequestDTO();
        Swipe customSwipeRecord = new Swipe();
        when(swipeService.createCustomSwipeRecord(any(), eq(empId))).thenReturn(customSwipeRecord);

        ResponseEntity<SwipeResponseDTO> response = swipeController.createCustomSwipeRecord(swipeDTO, empId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customSwipeRecord, SwipeObjectMapper.swipeDTOToSwipe(response.getBody()));
    }
}
