package com.example.acpaccounting.api.controllers;
import com.example.acpaccounting.api.dtos.paymentDtos.CreatePaymentDto;
import com.example.acpaccounting.api.dtos.paymentDtos.UpdatePaymentDto;
import com.example.acpaccounting.business.abstracts.PaymentService;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        super();
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public Result getAll(){return paymentService.getAll();}

    @PostMapping("/add")
    public DataResult<Payment> add(CreatePaymentDto createPaymentDto){return paymentService.add(createPaymentDto);}

    @PutMapping("/update")
    public Result update(UpdatePaymentDto updatePaymentDto){return paymentService.update(updatePaymentDto);}

    @DeleteMapping("/delete/{id}")
    public Result delete(int id){return paymentService.delete(id);};

    @GetMapping("/get/{id}")
    public DataResult<Payment> get(int id){return paymentService.get(id);}
}
