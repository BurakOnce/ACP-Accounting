package com.example.acpaccounting.entities.abstracts.dtos.paymentDtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdatePaymentDto {
    private int id;
    private double amount;
    private String description;
    private LocalDate paymentDate;
    private LocalDate  lastPaymentDate;
    private String owed;
    private int departmentId;
    private Integer invoiceId;

}
