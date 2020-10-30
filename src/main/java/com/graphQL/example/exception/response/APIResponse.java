package com.graphQL.example.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class APIResponse {

	@JsonProperty("data")
	private Object data=null;

	@JsonProperty("message")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message=null;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public APIResponse(Object data, String message) {
		this.data = data;
		this.message = message;
	}

	public APIResponse(Object data) {
		this.data = data;
	}

	public APIResponse(String message) {
		this.message = message;
	}

	public APIResponse() {
	}

	public static APIResponse ok(Object object, String message){
		return new APIResponse(object,message);
	}

	public static APIResponse ok(Object object){
		return new APIResponse(object);
	}

	public static APIResponse ok(String message){
		return new APIResponse(message);
	}
}
