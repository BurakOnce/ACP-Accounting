package com.example.acpaccounting.entities.concretes.enums;

public enum InvoiceType {

    OWN_PAYMENT("Own Payment"),
    RECEIVED_PAYMENT("Received Payment");


    private String Type;

    InvoiceType(String type) {
        this.Type = type;
    }

    public String getInvoiceType() {
        return Type;
    }
}
