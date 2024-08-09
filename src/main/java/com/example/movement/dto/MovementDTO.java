package com.example.movement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MovementDTO {

    private LocalDateTime timestamp;
    private MovementType type;
    private double amount;
    private String accountNumber;
}
