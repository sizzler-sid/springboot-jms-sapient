package com.training.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpAttendanceResponseDTO {

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
