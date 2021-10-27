package com.clinicallab.service;

import com.clinicallab.bindings.AppointmentForm;
import com.clinicallab.bindings.ClinicalDataRequest;
import com.clinicallab.bindings.PatientForm;
import com.clinicallab.exception.ClinicalException;
import com.clinicallab.exception.DoctorNotFoundException;
import com.clinicallab.exception.PatientNotFoundException;
import com.clinicallab.model.Doctor;

public interface ClinicalService {
	
	
	public boolean savePatient(PatientForm patientForm)throws PatientNotFoundException;
	public boolean saveDoctor(Doctor doctor) throws DoctorNotFoundException;
	public boolean saveClinicalData(ClinicalDataRequest request) throws PatientNotFoundException;
	public boolean addAppointmentDetails(AppointmentForm appt)throws ClinicalException;
	
	
}
