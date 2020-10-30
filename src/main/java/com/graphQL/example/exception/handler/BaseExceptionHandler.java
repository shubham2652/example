package com.graphQL.example.exception.handler;

public abstract class BaseExceptionHandler extends RuntimeException{

	public BaseExceptionHandler() {
		super();
	}

	public BaseExceptionHandler(String message) {
		super(message);
	}

	public BaseExceptionHandler(Throwable cause) {
		super(cause);
	}
}
