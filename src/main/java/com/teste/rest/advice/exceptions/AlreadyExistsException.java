package com.teste.rest.advice.exceptions;

public class AlreadyExistsException extends RuntimeException {
	public AlreadyExistsException(String message) {
		super(message);
	}
}
