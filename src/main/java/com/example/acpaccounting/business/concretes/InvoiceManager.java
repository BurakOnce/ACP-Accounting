package com.example.acpaccounting.business.concretes;

import com.example.acpaccounting.api.dtos.invoiceDtos.CreateInvoiceDto;
import com.example.acpaccounting.api.dtos.invoiceDtos.UpdateInvoiceDto;
import com.example.acpaccounting.business.abstracts.InvoiceService;
import com.example.acpaccounting.core.utilities.results.*;
import com.example.acpaccounting.dataAccess.abstracts.DepartmentRepository;
import com.example.acpaccounting.dataAccess.abstracts.InvoiceRepository;
import com.example.acpaccounting.dataAccess.abstracts.PaymentRepository;
import com.example.acpaccounting.entities.concretes.Department;
import com.example.acpaccounting.entities.concretes.Invoice;
import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceManager implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final DepartmentRepository departmentRepository;

    public InvoiceManager(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository, DepartmentRepository departmentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.departmentRepository = departmentRepository;
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
            invoice.setInvoiceStatus(createInvoiceDto.getInvoiceStatus());
            invoice.setDepartmentId(createInvoiceDto.getDepartmentId());

            Optional<Payment> payment = paymentRepository.findById(createInvoiceDto.getPaymentId());
            if (payment.isPresent()) {
                invoice.setPayment(payment.get());
            } else {
                return new FailDataResult("Payment not found for the provided paymentId");
            }

            invoiceRepository.save(invoice);
            return new SuccessDataResult("Request for create new Invoice", invoice);
        } catch (Exception exception) {
            return new FailDataResult("There was an error creating the Invoice", createInvoiceDto);
        }
    }

    @Override
    public Result update(UpdateInvoiceDto updateInvoiceDto) {
        try {
            Optional<Invoice> optionalInvoice = invoiceRepository.findById(updateInvoiceDto.getId());
            if (optionalInvoice.isPresent()) {
                Invoice invoice = optionalInvoice.get();

                invoice.setInvoiceType(updateInvoiceDto.getInvoiceType());
                invoice.setInvoiceNumber(updateInvoiceDto.getInvoiceNumber());
                invoice.setCustomerName(updateInvoiceDto.getCustomerName());
                invoice.setIssueDate(updateInvoiceDto.getIssueDate());
                invoice.setDueDate(updateInvoiceDto.getDueDate());
                invoice.setTotalAmount(updateInvoiceDto.getTotalAmount());
                invoice.setInvoiceStatus(updateInvoiceDto.getInvoiceStatus());
                invoice.setDepartmentId(updateInvoiceDto.getDepartmentId());

                Optional<Payment> payment = paymentRepository.findById(updateInvoiceDto.getPaymentId());
                if (payment.isPresent()) {
                    invoice.setPayment(payment.get());
                } else {
                    return new FailDataResult("Payment not found for the provided paymentId");
                }

                invoiceRepository.save(invoice);
                return new SuccessDataResult("Request for update Invoice", invoice);
            }
        } catch (Exception exception) {
            return new FailDataResult("There was an error updating the Invoice", updateInvoiceDto);
        }
        return new FailResult("Unexpected fail");
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
    public DataResult<Invoice> getByPaymentId(int paymentId) {
        Optional<Invoice> invoice = invoiceRepository.findByPayment_Id(paymentId);
        if (invoice.isPresent()) {
            return new SuccessDataResult("Get Invoice with Payment Id", invoice.get());
        } else {
            return new FailDataResult("No Invoice found for the given Payment Id");
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
