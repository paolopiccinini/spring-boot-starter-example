package com.example.springbootstarterexample.exception;

public class SomeEntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1921625614927119757L;

	public SomeEntityNotFoundException(String message) {
		super(message);
	}

}
