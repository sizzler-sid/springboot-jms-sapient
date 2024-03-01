package com.training.service;

import java.time.LocalDate;

public interface KafkaProducerService {

    public void deriveAttendance(LocalDate date);

}
