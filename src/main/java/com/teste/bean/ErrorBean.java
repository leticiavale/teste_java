package com.teste.bean;

public class ErrorBean {
	private String error;
	private String message;
	
	public ErrorBean() {
	}
	
	public ErrorBean(String error, String message) {
		this.error = error;
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
