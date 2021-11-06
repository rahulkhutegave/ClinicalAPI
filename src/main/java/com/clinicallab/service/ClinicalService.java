package com.clinicallab.service;

import java.util.List;

import com.clinicallab.bindings.AppointmentForm;
import com.clinicallab.bindings.ClinicalDataRequest;
import com.clinicallab.bindings.PatientForm;
import com.clinicallab.exception.ClinicalException;
import com.clinicallab.exception.DoctorNotFoundException;
import com.clinicallab.exception.PatientNotFoundException;
import com.clinicallab.model.ClinicalData;
import com.clinicallab.model.Doctor;
import com.clinicallab.model.Patient;

public interface ClinicalService {
	
	
	public boolean savePatient(PatientForm patientForm)throws PatientNotFoundException;
	public boolean saveDoctor(Doctor doctor) throws DoctorNotFoundException;
	public boolean saveClinicalData(ClinicalDataRequest request) throws PatientNotFoundException;
	public boolean addAppointmentDetails(AppointmentForm appt)throws ClinicalException;
	public List<Patient> getPatients(int pageNo, int size);
	public List<ClinicalData> getClinicalData(int id) throws PatientNotFoundException;
	public List<Patient> sortPatientsByAge(int pageNo);
	public String emailCheck(String email, String regexPattern);
	
}
