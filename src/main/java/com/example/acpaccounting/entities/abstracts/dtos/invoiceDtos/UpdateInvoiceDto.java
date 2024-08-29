package com.example.acpaccounting.entities.abstracts.dtos.invoiceDtos;

import com.example.acpaccounting.entities.concretes.enums.InvoiceStatus;
import com.example.acpaccounting.entities.concretes.enums.InvoiceType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateInvoiceDto {
    private int id;
    private InvoiceType invoiceType;
    private String invoiceNumber;
    private String customerName;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Double totalAmount;
    private String invoiceOwnerEmail;
    private String invoiceReceivedEmail;
    private InvoiceStatus invoiceStatus;
    private int departmentId;
}
