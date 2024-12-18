package com.aman.ems_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeNotFoundException(EmployeeNotFoundException exception){
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("Time", LocalDateTime.now());
        responseObject.put("Message", exception.getMessage());
        responseObject.put("StatusCode", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
    }
}
