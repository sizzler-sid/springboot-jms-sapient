package com.training.service.Impl;

import com.training.entity.Employee;
import com.training.entity.Swipe;
import com.training.exception.CustomException;
import com.training.model.Attendance;
import com.training.service.KafkaProducerService;
import com.training.utility.AttendanceObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private KafkaTemplate<String, Attendance> kafkaTemplate;
    private final static String TOPIC_NAME1 = "AttendanceTopic";

    private void postJsonMessage(Attendance attendance) {
        try {
            kafkaTemplate.send(TOPIC_NAME1, attendance);
            logger.info("Sent employee {} to Kafka topic {}", attendance.getEmpId(),TOPIC_NAME1);
        } catch (Exception e) {
            logger.error("Failed to send employee {} to Kafka topic", attendance.getEmpId(), e);
            throw new CustomException("Failed to produce message to Kafka topic", e);
        }
    }

    public void deriveAttendance(LocalDate date) {
        try {
            List<Employee> empList = employeeService.findAllByDate(date);
            logger.debug("Deriving attendance for employees");

            for(Employee emp : empList) {
                List<Swipe> swipes = emp.getSwipe();

                Map<Boolean, List<Swipe>> partition =
                        swipes.stream().collect(Collectors.partitioningBy(e -> e.getSwipeType().equals("SWIPE_IN")));
                List<Swipe> listSwipeIn = partition.get(true);
                List<Swipe> listSwipeOut = partition.get(false);

                Swipe swipeIn = listSwipeIn.get(0);
                Swipe swipeOut = listSwipeOut.get(listSwipeOut.size() - 1);

                Duration duration = Duration.between(swipeIn.getDateTime(), swipeOut.getDateTime());
                long hoursPresent = duration.toHours();

                String attendanceStatus;

                if (hoursPresent < 4L)
                    attendanceStatus = "Absent";
                else if (hoursPresent > 4L && hoursPresent < 8L)
                    attendanceStatus = "Half Day";
                else
                    attendanceStatus = "Present";

                Attendance attendance = AttendanceObjectMapper.convertToAttendanceObject(emp, swipeIn, swipeOut, attendanceStatus, hoursPresent);
                postJsonMessage(attendance);

            }
        } catch (Exception e) {
            logger.error("An error occurred while deriving attendance");
            throw new CustomException("Failed to derive attendance", e);
        }

    }

}
