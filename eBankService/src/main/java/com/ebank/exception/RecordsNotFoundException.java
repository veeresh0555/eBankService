package com.ebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RecordsNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	public RecordsNotFoundException(String message,Throwable t) {
		// TODO Auto-generated constructor stub
		super(message,t);
		
	}
	
	
	
	
}
