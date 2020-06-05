package com.ahackannection.exception;

public class ValidationException extends Exception {
	
	private String exceptNotify;
	
	public ValidationException(String notification) {
		exceptNotify = notification;		
	} 
	
	public String getExceptNotify() {
		return exceptNotify;
	}
	
}
