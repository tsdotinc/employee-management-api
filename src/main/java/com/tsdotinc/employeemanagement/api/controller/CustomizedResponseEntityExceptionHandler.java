package com.tsdotinc.employeemanagement.api.controller;

import java.sql.Date;

import com.tsdotinc.employeemanagement.api.exception.DatabaseException;
import com.tsdotinc.employeemanagement.api.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tsdotinc.employeemanagement.api.exception.EmployeeNotFoundException;
import com.tsdotinc.employeemanagement.api.model.ErrorResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// Custom EmployeeNotFoundException Handler
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(0), "EMP_NOT_FOUND", ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// Custom DatabaseException Handler
	@ExceptionHandler(DatabaseException.class)
	public final ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(0), "DB_ERROR", ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Custom ValidationException Handler
	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(0), "VALIDATION_ERROR", ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
