package com.training.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.entity.Employee;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SwipeResponseDTO {

    private Integer id;
    private String swipeType;
    private LocalDateTime dateTime;
    private LocalDate date;

}
