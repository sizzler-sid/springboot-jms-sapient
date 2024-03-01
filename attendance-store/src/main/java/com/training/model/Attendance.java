package com.training.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Attendance {
    private Integer empId;
    private String name;
    private String gender;
    private String department;
    private LocalDateTime firstSwipeIn;
    private LocalDateTime LastSwipeOut;
    private LocalDate date;
    private Long noOfHours;
    private String attendanceStatus;
}
