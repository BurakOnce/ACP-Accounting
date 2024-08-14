package com.example.acpaccounting.api.dtos.paymentDtos;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreatePaymentDto {
    private double amount;
    private String description;
    private LocalDate paymentDate;
    private LocalDate  lastPaymentDate;
    private String owed;
}