package com.aman.ems_backend.mapper;

import com.aman.ems_backend.dto.EmployeeDto;
import com.aman.ems_backend.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto employeeToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee employeeDtoToEmployee(EmployeeDto employee){
        return new Employee(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}
