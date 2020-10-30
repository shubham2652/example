package com.graphQL.example.exception.handler;

public class ValidationException extends BaseExceptionHandler {

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}
