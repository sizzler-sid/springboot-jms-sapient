package com.training.service;

import com.training.model.Attendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService
{
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    AttendanceServiceImpl attendanceService;

    @KafkaListener(topics = "AttendanceTopic", groupId = "group1",containerFactory = "attendanceListener")
    public void listen(List<Attendance> attendances)
    {
        logger.info("Received Kafka messages");
        try {
            System.out.println(attendances);
            attendanceService.saveAttendance(attendances);
            logger.info("Processed {} Kafka messages", attendances.size());
        } catch (Exception e) {
            logger.error("Error processing Kafka messages: {}", e.getMessage(), e);
        }
    }
}
