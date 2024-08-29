package com.example.acpaccounting.api.controllers;

import com.example.acpaccounting.entities.abstracts.dtos.invoiceDtos.CreateInvoiceDto;
import com.example.acpaccounting.entities.abstracts.dtos.invoiceDtos.UpdateInvoiceDto;
import com.example.acpaccounting.business.abstracts.InvoiceService;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Invoice>>> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<Invoice>> add(@RequestBody CreateInvoiceDto createInvoiceDto) {
        return ResponseEntity.ok(invoiceService.add(createInvoiceDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        return ResponseEntity.ok(invoiceService.delete(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DataResult<Invoice>> get(@PathVariable int id) {
        return ResponseEntity.ok(invoiceService.get(id));
    }

    @GetMapping("/getDepartmentName/{invoiceId}")
    public ResponseEntity<String> getDepartmentName(@PathVariable int invoiceId) {
        return ResponseEntity.ok(invoiceService.getDepartmentName(invoiceId));
    }

    @GetMapping("/getDepartmentId/{invoiceId}")
    public ResponseEntity<Integer> getDepartmentId(@PathVariable int invoiceId) {
        return ResponseEntity.ok(invoiceService.getDepartmentId(invoiceId));
    }
}
