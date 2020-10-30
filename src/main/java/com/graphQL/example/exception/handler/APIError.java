package com.graphQL.example.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class APIError {

	@JsonIgnore
	private HttpStatus httpStatus;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("errors")
	private Set<String> errors;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Set<String> getErrors() {
		return errors;
	}

	public APIError(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public APIError(HttpStatus httpStatus, Set<String> errors) {
		this.httpStatus = httpStatus;
		this.errors = errors;
	}

	public APIError() {
	}

	public APIError(HttpStatus httpStatus, String errorMessage) {
		this.httpStatus = httpStatus;
		addSubError(errorMessage);
	}

	public APIError(HttpStatus httpStatus, Throwable ex) {
		this.httpStatus = httpStatus;
		addSubError(ex.getLocalizedMessage());
	}

	public APIError(HttpStatus httpStatus, List<ObjectError> objectErrors) {
		this.httpStatus = httpStatus;
		addValidationErrors(objectErrors);
	}

	public void addSubError(String error){
		if(Objects.isNull(this.errors)){
			this.errors = new HashSet<>();
		}
		this.errors.add(error);
	}

	private void addValidationError(String message) {
		addSubError(message);
	}

	private void addValidationError(ObjectError fieldError) {
		this.addValidationError(fieldError.getDefaultMessage());
	}

	private void addValidationErrors(List<ObjectError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}
}
