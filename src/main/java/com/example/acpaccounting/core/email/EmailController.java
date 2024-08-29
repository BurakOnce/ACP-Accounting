package com.example.acpaccounting.core.email;

import com.example.acpaccounting.entities.concretes.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/send-email")
public class EmailController {

    @Autowired
    private EmailSenderService senderService;

    @PostMapping
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        senderService.sendSimpleEmail(
                emailRequest.getToEmails(),
                emailRequest.getSubject(),
                emailRequest.getBody());
        return "Mail sent successfully";
    }

    @PostMapping("/send-invoice")
    public String sendInvoiceEmail(@RequestBody Invoice invoice) {

        String subject = "Fatura: " + invoice.getInvoiceNumber();
        String body = "Merhaba " + invoice.getCustomerName() + ",\n\n" +
                "Fatura Tarihi: " + invoice.getIssueDate() + "\n" +
                "Son Ödeme Tarihi: " + invoice.getDueDate() + "\n" +
                "Toplam Tutar: " + invoice.getTotalAmount() + "\n\n" +
                "Teşekkür ederiz.";


        senderService.sendSimpleEmail(
                Arrays.asList(invoice.getInvoiceOwnerEmail(), invoice.getInvoiceReceivedEmail()),
                subject,
                body);

        return "Invoice mail sent successfully";
    }

    @GetMapping("/get-message")
    public String getMessage() {
        return "get message";
    }
}
