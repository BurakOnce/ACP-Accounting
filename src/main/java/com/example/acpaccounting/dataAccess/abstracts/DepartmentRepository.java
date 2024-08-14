package com.example.acpaccounting.dataAccess.abstracts;

import com.example.acpaccounting.entities.concretes.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}