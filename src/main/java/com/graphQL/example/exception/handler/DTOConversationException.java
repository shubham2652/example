package com.graphQL.example.exception.handler;

import com.fasterxml.jackson.databind.ser.Serializers;

public class DTOConversationException extends BaseExceptionHandler {

	public DTOConversationException(String message) {
		super(message);
	}
}
