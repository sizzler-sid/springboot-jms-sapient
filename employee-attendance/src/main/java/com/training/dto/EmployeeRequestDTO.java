package com.training.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

    private String name;
    private String gender;
    private String department;

}
