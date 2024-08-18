package com.example.acpaccounting.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="amount",nullable = false)
    private double amount;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="payment_date",nullable = false)
    private LocalDate  paymentDate;

    @Column(name="last_payment_date",nullable = false)
    private LocalDate  lastPaymentDate;

    @Column(name="owed",nullable = false)
    private String owed;

    @Column(name="departman_id",nullable = false)
    private int departmentId;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, optional = true)
    private Invoice invoice;


}
