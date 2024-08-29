package com.example.acpaccounting.entities.abstracts.dtos.departmentDtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateDepartmentDto {
    private String name;
    private boolean status;
    private int employeesNumber;
    private double budget;
}
