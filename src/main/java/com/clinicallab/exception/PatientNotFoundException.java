package com.clinicallab.exception;

public class PatientNotFoundException extends ClinicalException {

	public PatientNotFoundException() {
		super();
	}

	public PatientNotFoundException(String message) {
		super(message);
	}

}
