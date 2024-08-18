package com.example.acpaccounting.entities.concretes.enums;

public enum InvoiceStatus {

    PAID ("Paid"),
    UNPAID("Unpaid"),
    CANCELLED("Cancelled");
    private String status;

    InvoiceStatus(String status) {
        this.status = status;
    }

    public String getInvoiceType() {
        return status;
    }
}
