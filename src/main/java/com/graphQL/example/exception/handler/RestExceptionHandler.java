package com.graphQL.example.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.Null;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


	private final ResponseEntity<Object> buildResponseEntity(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}

	private ResponseEntity<Object> internalServerErrorEntity(Exception ex) {
		APIError apiError = new APIError(
			HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error));
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		try {
			APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors());

			return new ResponseEntity<>(apiError, apiError.getHttpStatus());
		} catch (Exception e) {
			return internalServerErrorEntity(e);
		}
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex){
		try {
			APIError apiError = new APIError(HttpStatus.NOT_FOUND, ex.getMessage());
			return buildResponseEntity(apiError);
		}catch (Exception e){
			return internalServerErrorEntity(e);
		}
	}

	@ExceptionHandler(DTOConversationException.class)
	protected ResponseEntity<Object> handleDTOConversationException(DTOConversationException ex){
		return internalServerErrorEntity(ex);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleDTOConversationException(NullPointerException ex){
		return internalServerErrorEntity(ex);
	}

	@ExceptionHandler(BaseConversationException.class)
	protected ResponseEntity<Object> handleBaseConversationException(BaseConversationException ex){
		return internalServerErrorEntity(ex);
	}

//	@ExceptionHandler({ BuisnessValidationException.class })
//	protected ResponseEntity<Object> handleBusinessValidation(BuisnessValidationException be, WebRequest request) {
//		try {
//			APIError apiError = null;
//			if (CollectionUtil.nonNullNonEmpty(be.getErrors())) {
//				for (String err : be.getErrors()) {
//					apiError = new ApiError(HttpStatus.BAD_REQUEST);
//					apiError.addSubError(err);
//				}
//			} else {
//				apiError = new ApiError(HttpStatus.BAD_REQUEST, be);
//			}
//			return buildResponseEntity(apiError);
//		} catch (Exception e) {
//			/**
//			 * Suppressing technical level error if in case RestException is unable to
//			 * Handle it
//			 */
//			return internalServerErrorEntity(e);
//		}
//	}

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleValidationException(ValidationException ex){
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return buildResponseEntity(apiError);
	}
}
