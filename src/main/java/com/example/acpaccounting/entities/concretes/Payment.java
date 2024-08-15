package com.example.acpaccounting.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double amount;

    private String description;

    @Column(nullable = false)
    private LocalDate  paymentDate;

    private LocalDate  lastPaymentDate;

    @Column(nullable = false)
    private String owed;

    @Column(name="departman_Id",nullable = false)
    private int departmentId;


}
