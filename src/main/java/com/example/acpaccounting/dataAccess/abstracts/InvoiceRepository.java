package com.example.acpaccounting.dataAccess.abstracts;

import com.example.acpaccounting.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
