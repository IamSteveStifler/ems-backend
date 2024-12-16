package com.aman.ems_backend.service.Impl;

import com.aman.ems_backend.dto.EmployeeDto;
import com.aman.ems_backend.entity.Employee;
import com.aman.ems_backend.exception.EmployeeNotFoundException;
import com.aman.ems_backend.mapper.EmployeeMapper;
import com.aman.ems_backend.repository.EmployeeRepository;
import com.aman.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee result = employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee with given id: "+id+" doesn't exists"));
        return EmployeeMapper.employeeToEmployeeDto(result);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(savedEmployee);
    }
}


