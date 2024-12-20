package com.aman.ems_backend.exception;

public class EmployeeAlreadyExistException extends RuntimeException{

    public EmployeeAlreadyExistException(String message){
        super(message);
    }

}
