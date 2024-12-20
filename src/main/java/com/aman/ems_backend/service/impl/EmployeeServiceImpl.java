package com.aman.ems_backend.service.impl;

import com.aman.ems_backend.dto.EmployeeDto;
import com.aman.ems_backend.entity.Employee;
import com.aman.ems_backend.exception.EmployeeAlreadyExistException;
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
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.employeeDtoToEmployee(employeeDto);
        if(employee.getFirstName() == null ||
                employee.getLastName() == null ||
                employee.getEmail() == null) {
            throw new EmployeeAlreadyExistException("Data missing");
        }
        employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee doesn't exist with id: "+id));

        return EmployeeMapper.employeeToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> allEmployeeList = new ArrayList<>();
        employeeRepository
                .findAll()
                .forEach(employee -> allEmployeeList.add(EmployeeMapper.employeeToEmployeeDto(employee)));
        return allEmployeeList;
    }
}
