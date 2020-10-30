package com.graphQL.example.exception.handler;

public class BuisnessValidationException extends BaseExceptionHandler{

	public BuisnessValidationException(String message) {
		super(message);
	}

	public BuisnessValidationException(Throwable cause) {
		super(cause);
	}
}
