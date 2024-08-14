package com.example.acpaccounting.business.abstracts;
import com.example.acpaccounting.api.dtos.paymentDtos.CreatePaymentDto;
import com.example.acpaccounting.api.dtos.paymentDtos.UpdatePaymentDto;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Payment;

public interface PaymentService {
    Result getAll();
    DataResult<Payment> add(CreatePaymentDto createPaymentDto);
    Result update(UpdatePaymentDto updatePaymentDto);
    Result delete(int id);
    DataResult<Payment> get(int id);
}
