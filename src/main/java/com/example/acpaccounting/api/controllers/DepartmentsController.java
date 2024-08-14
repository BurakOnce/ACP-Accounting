package com.example.acpaccounting.api.controllers;

import com.example.acpaccounting.api.dtos.departmentDtos.CreateDepartmentDto;
import com.example.acpaccounting.api.dtos.departmentDtos.UpdateDepartmentDto;
import com.example.acpaccounting.business.abstracts.DepartmentService;
import com.example.acpaccounting.core.utilities.results.DataResult;
import com.example.acpaccounting.core.utilities.results.Result;
import com.example.acpaccounting.entities.concretes.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentsController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public Result getAll(){return departmentService.getAll();}

    @PostMapping("/add")
    public DataResult<Department> add(CreateDepartmentDto createDepartmentDto){return departmentService.add(createDepartmentDto);}

    @PutMapping("/update")
    public Result update(UpdateDepartmentDto updateDepartmentDto){return departmentService.update(updateDepartmentDto);}

    @DeleteMapping("/delete/{id}")
    public Result delete(int id){return departmentService.delete(id);};

    @GetMapping("/get/{id}")
    public DataResult<Department> get(int id){return departmentService.get(id);}
}
