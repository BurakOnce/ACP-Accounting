package com.example.acpaccounting.api.dtos.departmentDtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UpdateDepartmentDto {
    private int id;
    private String name;
    private boolean status;
    private int employeesNumber;
    private double budget;
}
