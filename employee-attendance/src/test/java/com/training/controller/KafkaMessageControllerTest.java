package com.training.controller;

import com.training.service.Impl.KafkaProducerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KafkaMessageControllerTest {

    @Mock
    private KafkaProducerServiceImpl kafkaProducerService;

    @InjectMocks
    private KafkaMessageController kafkaMessageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendKafkaMessage() {
        LocalDate date = LocalDate.now();

        doNothing().when(kafkaProducerService).deriveAttendance(date);

        ResponseEntity<String> response = kafkaMessageController.sendKafkaMessage(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Kafka message sent successfully", response.getBody());
        verify(kafkaProducerService, times(1)).deriveAttendance(date);
    }
}
