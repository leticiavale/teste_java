package com.teste.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teste.bean.ErrorBean;
import com.teste.rest.advice.exceptions.AlreadyExistsException;
import com.teste.rest.advice.exceptions.InvalidFieldException;
import com.teste.rest.advice.exceptions.NotFoundException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidFieldException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorBean exception(InvalidFieldException ex) {
    	return new ErrorBean("InvalidFieldException",ex.getMessage());
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorBean exception(AlreadyExistsException ex) {
    	return new ErrorBean("AlreadyExistsException",ex.getMessage());
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorBean exception(NotFoundException ex) {
    	return new ErrorBean("NotFoundException",ex.getMessage());
    }
	
}