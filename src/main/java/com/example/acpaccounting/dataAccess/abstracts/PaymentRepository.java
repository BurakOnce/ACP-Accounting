package com.example.acpaccounting.dataAccess.abstracts;

import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
