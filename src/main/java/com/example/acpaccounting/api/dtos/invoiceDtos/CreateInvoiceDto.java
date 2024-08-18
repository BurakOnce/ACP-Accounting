package com.example.acpaccounting.api.dtos.invoiceDtos;

import com.example.acpaccounting.entities.concretes.enums.InvoiceStatus;
import com.example.acpaccounting.entities.concretes.enums.InvoiceType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateInvoiceDto {
    private InvoiceType invoiceType;
    private String invoiceNumber;
    private String customerName;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Double totalAmount;
    private InvoiceStatus invoiceStatus;
    private int departmentId;
    private int paymentId;
}
