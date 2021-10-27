package com.clinicallab.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(value = PatientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePatientException(PatientNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("pat100");
		response.setErrorMsg(e.getMessage() + "Patient Not Found");
		response.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = DoctorNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDoctorException(DoctorNotFoundException e){
		
		ErrorResponse response=new ErrorResponse();
		response.setErrorCode("Doc101");
		response.setErrorMsg(e.getMessage()+"Doctor Not Found");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ClinicalException.class)
	public ResponseEntity<ErrorResponse> handleClinicalException(ClinicalException e){
		ErrorResponse response=new ErrorResponse();
		response.setErrorCode("Cln102");
		response.setErrorMsg(e.getMessage()+"Please Give Correct Input");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

}
