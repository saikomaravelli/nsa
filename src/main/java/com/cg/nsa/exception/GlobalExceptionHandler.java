package com.cg.nsa.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidInstitutionException.class)
	public ResponseEntity<?> invalidInstitutionException(InvalidInstitutionException i, WebRequest request){
//		return new ResponseEntity(i.getMessage(),HttpStatus.NOT_FOUND);
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), i.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
