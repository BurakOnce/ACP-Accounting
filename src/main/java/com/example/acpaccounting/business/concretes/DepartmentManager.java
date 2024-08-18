package com.example.acpaccounting.business.concretes;

import com.example.acpaccounting.api.dtos.departmentDtos.CreateDepartmentDto;
import com.example.acpaccounting.api.dtos.departmentDtos.UpdateDepartmentDto;
import com.example.acpaccounting.business.abstracts.DepartmentService;
import com.example.acpaccounting.core.utilities.results.*;
import com.example.acpaccounting.dataAccess.abstracts.DepartmentRepository;
import com.example.acpaccounting.entities.concretes.Department;
import com.example.acpaccounting.entities.concretes.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentManager implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentManager(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }
    @Override
    public Result getAll() {
        try{
            return new SuccessDataResult("Request for list all Departments",departmentRepository.findAll());
        }catch(Exception exception){
            return new FailResult("Unsuccessful to list all Departments");
        }    }

    @Override
    public DataResult<Department> add(CreateDepartmentDto createDepartmentDto) {
        try{
            Department department= new Department();

            department.setName(createDepartmentDto.getName());
            department.setBudget(createDepartmentDto.getBudget());
            department.setEmployeeNumber(createDepartmentDto.getEmployeesNumber());
            department.setStatus(createDepartmentDto.isStatus());

            departmentRepository.save(department);
            return new SuccessDataResult("Request for create new Payment",department);
        }catch (Exception exception){
            return new FailDataResult("There was an error creating the Payment",createDepartmentDto);
        }    }

    @Override
    public Result update(UpdateDepartmentDto updateDepartmentDto) {

        try{
            Optional<Department> optionalDepartment = departmentRepository.findById(updateDepartmentDto.getId());
            if(optionalDepartment.isPresent()){
                Department department=optionalDepartment.get();
                department.setName(updateDepartmentDto.getName());
                department.setBudget(updateDepartmentDto.getBudget());
                department.setEmployeeNumber(updateDepartmentDto.getEmployeesNumber());
                department.setStatus(updateDepartmentDto.isStatus());

                departmentRepository.save(department);
                return new SuccessDataResult("Request for update Department",department);
            }
        }catch (Exception exception){
            return new FailDataResult("There was an error updating the Department",updateDepartmentDto);
        }
        return new FailResult("Unexpected fail");    }

    @Override
    public Result delete(int id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if(optionalDepartment.isPresent()){
            departmentRepository.deleteById(id);
            return new SuccessResult("Successfully deleted Department");
        }else{
            return new FailResult("There is no Department for this Id");
        }    }

    @Override
    public DataResult<Department> get(int id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if(optionalDepartment.isPresent()){
            return new SuccessDataResult("Get Department with Id",optionalDepartment);
        }else{
            return new FailDataResult("There is no Department for this Id");
        }    }
}
