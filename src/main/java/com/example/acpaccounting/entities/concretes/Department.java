package com.example.acpaccounting.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "employee_number", nullable = false)
    private int employeeNumber;

    @Column(name = "budget", nullable = false)
    private double budget;
}
