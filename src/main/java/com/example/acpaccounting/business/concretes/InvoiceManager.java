package com.example.acpaccounting.business.concretes;

import com.example.acpaccounting.core.email.EmailSenderService;
import com.example.acpaccounting.entities.abstracts.dtos.invoiceDtos.CreateInvoiceDto;
import com.example.acpaccounting.business.abstracts.InvoiceService;
import com.example.acpaccounting.core.utilities.results.*;
import com.example.acpaccounting.dataAccess.abstracts.DepartmentRepository;
import com.example.acpaccounting.dataAccess.abstracts.InvoiceRepository;
import com.example.acpaccounting.entities.concretes.Department;
import com.example.acpaccounting.entities.concretes.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceManager implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final EmailSenderService emailSenderService;


    public InvoiceManager(InvoiceRepository invoiceRepository, DepartmentRepository departmentRepository, EmailSenderService emailSenderService) {
        this.invoiceRepository = invoiceRepository;
        this.departmentRepository = departmentRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public DataResult<List<Invoice>> getAll() {
        try {
            return new SuccessDataResult("Request for list all Invoices", invoiceRepository.findAll());
        } catch (Exception exception) {
            return new FailDataResult("Unsuccessful to list all Invoices");
        }
    }

    @Override
    public DataResult<Invoice> add(CreateInvoiceDto createInvoiceDto) {
        try {
            Invoice invoice = new Invoice();

            invoice.setInvoiceType(createInvoiceDto.getInvoiceType());
            invoice.setInvoiceNumber(createInvoiceDto.getInvoiceNumber());
            invoice.setCustomerName(createInvoiceDto.getCustomerName());
            invoice.setIssueDate(createInvoiceDto.getIssueDate());
            invoice.setDueDate(createInvoiceDto.getDueDate());
            invoice.setTotalAmount(createInvoiceDto.getTotalAmount());
            invoice.setInvoiceOwnerEmail(createInvoiceDto.getInvoiceOwnerEmail());
            invoice.setInvoiceReceivedEmail(createInvoiceDto.getInvoiceReceivedEmail());
            invoice.setInvoiceStatus(createInvoiceDto.getInvoiceStatus());
            invoice.setDepartmentId(createInvoiceDto.getDepartmentId());

            // Faturayı kaydetme
            invoiceRepository.save(invoice);

            // Fatura kaydedildikten sonra e-posta gönderimi
            String subject = "Fatura: " + invoice.getInvoiceNumber();
            String body = "Merhaba " + invoice.getCustomerName() + ",\n\n" +
                    "Fatura Tarihi: " + invoice.getIssueDate() + "\n" +
                    "Son Ödeme Tarihi: " + invoice.getDueDate() + "\n" +
                    "Toplam Tutar: " + invoice.getTotalAmount() + "\n\n" +
                    "Teşekkür ederiz.";

            // İki e-posta adresine e-posta gönderimi
            emailSenderService.sendSimpleEmail(
                    Arrays.asList(invoice.getInvoiceOwnerEmail(), invoice.getInvoiceReceivedEmail()),
                    subject,
                    body
            );

            return new SuccessDataResult("Request for create new Invoice", invoice);
        } catch (Exception exception) {
            return new FailDataResult("There was an error creating the Invoice", createInvoiceDto);
        }
    }


    @Override
    public Result delete(int id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            invoiceRepository.deleteById(id);
            return new SuccessResult("Successfully deleted Invoice");
        } else {
            return new FailResult("There is no Invoice for this Id");
        }
    }

    @Override
    public DataResult<Invoice> get(int id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            return new SuccessDataResult("Get Invoice with Id", optionalInvoice.get());
        } else {
            return new FailDataResult("There is no Invoice for this Id");
        }
    }


    @Override
    public String getDepartmentName(int invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            int departmentId = invoice.get().getDepartmentId();
            Optional<Department> department = departmentRepository.findById(departmentId);
            return department.map(Department::getName).orElse("Department cannot found: " + departmentId);
        } else {
            return "Invoice cannot found: " + invoiceId;
        }
    }

    @Override
    public int getDepartmentId(int invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            return invoice.get().getDepartmentId();
        } else {
            return 404;
        }
    }
}
