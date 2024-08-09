package com.example.movement.entity;

import com.example.movement.dto.MovementType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
public class Movement {

    @Id
    private Long id;
    private LocalDateTime timestamp;
    private MovementType type;
    private double amount;
    private double balance;

    @OneToOne
    private Account account;
}