package com.training.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Integer id;
    private String name;
    private String gender;
    private String department;

}
