package com.graphQL.example.exception.handler;

public class BaseConversationException extends BaseExceptionHandler {

	public BaseConversationException(String message) {
		super(message);
	}

	public BaseConversationException(Throwable cause) {
		super(cause);
	}
}
