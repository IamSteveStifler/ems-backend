package com.aman.ems_backend.service.Impl;

import com.aman.ems_backend.dto.EmployeeDto;
import com.aman.ems_backend.entity.Employee;
import com.aman.ems_backend.exception.EmployeeNotFoundException;
import com.aman.ems_backend.mapper.EmployeeMapper;
import com.aman.ems_backend.repository.EmployeeRepository;
import com.aman.ems_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployeeById(Long id){
        Employee result = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee doesn't exist with id: "+id));
        return EmployeeMapper.employeeToEmployeeDto(result);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> allEmployees = new ArrayList<>();
        employeeRepository.findAll().forEach(
                employee -> allEmployees.add(EmployeeMapper.employeeToEmployeeDto(employee))
        );
        return allEmployees;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedDetails) {
        Employee fetchedEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee doesn't exist with id:"+ id)
                );
        fetchedEmployee.setFirstName(updatedDetails.getFirstName());
        fetchedEmployee.setLastName(updatedDetails.getLastName());
        fetchedEmployee.setEmail(updatedDetails.getEmail());
        return EmployeeMapper.employeeToEmployeeDto(employeeRepository.save(fetchedEmployee));
    }
}


