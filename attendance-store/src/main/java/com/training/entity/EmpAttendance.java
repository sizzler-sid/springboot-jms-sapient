package com.training.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "emp_attendance")
public class EmpAttendance {
    @Id
    private String id;
    private Integer empId;
    private String name;
    private String gender;
    private String department;
    private String firstSwipeIn;
    private String LastSwipeOut;
    private LocalDate date;
    private Long noOfHours;
    private String attendanceStatus;
}
