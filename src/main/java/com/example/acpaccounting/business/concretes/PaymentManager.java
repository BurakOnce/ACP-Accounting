package com.example.acpaccounting.business.concretes;

import com.example.acpaccounting.entities.abstracts.dtos.paymentDtos.CreatePaymentDto;
import com.example.acpaccounting.entities.abstracts.dtos.paymentDtos.UpdatePaymentDto;
import com.example.acpaccounting.business.abstracts.PaymentService;
import com.example.acpaccounting.core.utilities.results.*;
import com.example.acpaccounting.dataAccess.abstracts.DepartmentRepository;
import com.example.acpaccounting.dataAccess.abstracts.PaymentRepository;
import com.example.acpaccounting.entities.concretes.Department;
import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentManager implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final DepartmentRepository departmentRepository;
    public PaymentManager(PaymentRepository paymentRepository, DepartmentRepository departmentRepository) {
        this.paymentRepository = paymentRepository;
        this.departmentRepository = departmentRepository;
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
        try {
            Payment payment = new Payment();

            payment.setAmount(createPaymentDto.getAmount());
            payment.setDescription(createPaymentDto.getDescription());
            payment.setPaymentDate(createPaymentDto.getPaymentDate());
            payment.setLastPaymentDate(createPaymentDto.getLastPaymentDate());
            payment.setOwed(createPaymentDto.getOwed());
            payment.setDepartmentId(createPaymentDto.getDepartmentId());
            paymentRepository.save(payment);
            return new SuccessDataResult("Request for create new Payment", payment);
        } catch (Exception exception) {
            return new FailDataResult("There was an error creating the Payment", createPaymentDto);
        }
    }


    @Override
    public Result update(UpdatePaymentDto updatePaymentDto) {
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(updatePaymentDto.getId());
            if (optionalPayment.isPresent()) {
                Payment payment = optionalPayment.get();

                payment.setAmount(updatePaymentDto.getAmount());
                payment.setDescription(updatePaymentDto.getDescription());
                payment.setPaymentDate(updatePaymentDto.getPaymentDate());
                payment.setLastPaymentDate(updatePaymentDto.getLastPaymentDate());
                payment.setOwed(updatePaymentDto.getOwed());
                payment.setDepartmentId(updatePaymentDto.getDepartmentId());

                paymentRepository.save(payment);
                return new SuccessDataResult("Request for update Payment", payment);
            }
        } catch (Exception exception) {
            return new FailDataResult("There was an error updating the Payment", updatePaymentDto);
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

    @Override
    public String getDepartmentName(int paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (payment.isPresent()) {
            int departmentId = payment.get().getDepartmentId();
            Optional<Department> department = departmentRepository.findById(departmentId);
            if (department.isPresent()) {
                return department.get().getName();
            } else {
                return "Department cannot found: " + departmentId;
            }
        } else {
            return "Payment cannot found: " + paymentId;
        }
    }


    @Override
    public int getDepartmentId(int paymentId) {
        Optional<Payment> payment=paymentRepository.findById(paymentId);
        if (payment.isPresent()) {
            int departmentId = payment.get().getDepartmentId();
            Optional<Department> department = departmentRepository.findById(departmentId);
            return department.map(Department::getId).orElse(404);
        } else {
            return 404;
        }
    }


}
