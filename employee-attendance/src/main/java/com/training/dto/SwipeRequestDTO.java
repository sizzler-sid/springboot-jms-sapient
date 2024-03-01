package com.training.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SwipeRequestDTO {

    private String swipeType;
    private LocalDateTime dateTime;
    private LocalDate date;

}
