package com.tsdotinc.employeemanagement.api.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
	
	private Date timeStamp;
	private String errorCode;  // New field for error code
	private String message;
	private String details;
	
}
