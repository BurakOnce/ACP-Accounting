package com.example.acpaccounting.business.concretes;

import com.example.acpaccounting.api.dtos.paymentDtos.CreatePaymentDto;
import com.example.acpaccounting.api.dtos.paymentDtos.UpdatePaymentDto;
import com.example.acpaccounting.business.abstracts.PaymentService;
import com.example.acpaccounting.core.utilities.results.*;
import com.example.acpaccounting.dataAccess.abstracts.PaymentRepository;
import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentManager implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Result getAll() {
        try{
            return new SuccessDataResult("Request for list all Payments",paymentRepository.findAll());
        }catch(Exception exception){
            return new FailResult("Unsuccessful to list all Payments");
        }
    }

    @Override
    public DataResult<Payment> add(CreatePaymentDto createPaymentDto) {
        try{
            Payment payment= new Payment();

            payment.setAmount(createPaymentDto.getAmount());
            payment.setDescription(createPaymentDto.getDescription());
            payment.setPaymentDate(createPaymentDto.getPaymentDate());
            payment.setLastPaymentDate(createPaymentDto.getLastPaymentDate());
            payment.setOwed(createPaymentDto.getOwed());

            paymentRepository.save(payment);
            return new SuccessDataResult("Request for create new Payment",payment);
        }catch (Exception exception){
            return new FailDataResult("There was an error creating the Payment",createPaymentDto);
        }

    }

    @Override
    public Result update(UpdatePaymentDto updatePaymentDto) {
        try{
            Optional<Payment> optionalPayment = paymentRepository.findById(updatePaymentDto.getId());
            if(optionalPayment.isPresent()){
                Payment payment=optionalPayment.get();
                payment.setAmount(updatePaymentDto.getAmount());
                payment.setDescription(updatePaymentDto.getDescription());
                payment.setPaymentDate(updatePaymentDto.getPaymentDate());
                payment.setLastPaymentDate(updatePaymentDto.getLastPaymentDate());
                payment.setOwed(updatePaymentDto.getOwed());
                paymentRepository.save(payment);
                return new SuccessDataResult("Request for update Payment",payment);
            }
        }catch (Exception exception){
            return new FailDataResult("There was an error updating the Payment",updatePaymentDto);
        }
        return new FailResult("Unexpected fail");
    }

    @Override
    public Result delete(int id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if(optionalPayment.isPresent()){
            paymentRepository.deleteById(id);
            return new SuccessResult("Successfully deleted Payment");
        }else{
            return new FailResult("There is no Payment for this Id");
        }
    }

    @Override
    public DataResult<Payment> get(int id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if(optionalPayment.isPresent()){
            return new SuccessDataResult("Get Payment with Id",optionalPayment);
        }else{
            return new FailDataResult("There is no Payment for this Id");
        }
    }


}
