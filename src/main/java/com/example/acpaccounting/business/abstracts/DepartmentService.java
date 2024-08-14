package com.example.acpaccounting.business.abstracts;
import com.example.acpaccounting.api.dtos.departmentDtos.CreateDepartmentDto;
import com.example.acpaccounting.api.dtos.departmentDtos.UpdateDepartmentDto;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Department;

public interface DepartmentService {
    Result getAll();
    DataResult<Department> add(CreateDepartmentDto createDepartmentDto);
    Result update(UpdateDepartmentDto updateDepartmentDto);
    Result delete(int id);
    DataResult<Department> get(int id);
}
