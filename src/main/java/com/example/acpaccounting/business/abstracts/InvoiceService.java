package com.example.acpaccounting.business.abstracts;

import com.example.acpaccounting.api.dtos.invoiceDtos.CreateInvoiceDto;
import com.example.acpaccounting.api.dtos.invoiceDtos.UpdateInvoiceDto;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Invoice;


import java.util.List;

public interface InvoiceService {

    DataResult<List<Invoice>> getAll();

    DataResult<Invoice> add(CreateInvoiceDto createInvoiceDto);

    Result update(UpdateInvoiceDto updateInvoiceDto);

    Result delete(int id);

    DataResult<Invoice> get(int id);

    DataResult<Invoice> getByPaymentId(int paymentId);

    String getDepartmentName(int invoiceId);

    int getDepartmentId(int invoiceId);
}
