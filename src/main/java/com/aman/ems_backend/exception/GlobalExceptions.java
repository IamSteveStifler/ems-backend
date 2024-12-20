package com.aman.ems_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptions {

    public void setResponseDetails(Map<String , Object> response,
                                   String message, int value){
        response.put("Time", LocalDateTime.now());
        response.put("Message", message);
        response.put("Error", value);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException exception){
        Map<String , Object> response = new HashMap<>();
        setResponseDetails(response, exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<Object> employeeAlreadyExistException(EmployeeAlreadyExistException exception){
        Map<String , Object> response = new HashMap<>();
        setResponseDetails(response, exception.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
