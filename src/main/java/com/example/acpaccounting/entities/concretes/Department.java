package com.example.acpaccounting.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private boolean status;
    private int employeesNumber;
    private double budget;
}
