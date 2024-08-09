package com.example.movement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Account {
    @Id
    private Long id;
    private String number;
    private String type;
    private double initialBalance;
    private double availableBalance;
    private boolean status;

}
