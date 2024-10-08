package com.example.acpaccounting.entities.abstracts.dtos.paymentDtos;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreatePaymentDto {
    private double amount;
    private String description;
    private LocalDate paymentDate;
    private LocalDate  lastPaymentDate;
    private String owed;
    private int departmentId;
    private Integer invoiceId;

}