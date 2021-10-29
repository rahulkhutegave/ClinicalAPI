package com.clinicallab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.clinicallab.bindings.AppointmentForm;
import com.clinicallab.bindings.ClinicalDataRequest;
import com.clinicallab.bindings.PatientForm;
import com.clinicallab.exception.ClinicalException;
import com.clinicallab.exception.DoctorNotFoundException;
import com.clinicallab.exception.PatientNotFoundException;
import com.clinicallab.model.Appointment;
import com.clinicallab.model.ClinicalData;
import com.clinicallab.model.Doctor;
import com.clinicallab.model.Patient;
import com.clinicallab.repository.AppointmentRepo;
import com.clinicallab.repository.ClinicalDataRepo;
import com.clinicallab.repository.DoctorRepository;
import com.clinicallab.repository.PatientRepository;

@Service
public class ClinicalServiceImpl implements ClinicalService {

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private ClinicalDataRepo clinicalRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private AppointmentRepo appointmentRepo;

	@Override
	public boolean savePatient(PatientForm patientForm) throws PatientNotFoundException {

		Patient entity1 = new Patient();
		BeanUtils.copyProperties(patientForm, entity1);

		Patient patient = patientRepo.save(entity1);
		System.out.println(patient.getId());
		return patientRepo.existsById(patient.getId());
	}

	@Override
	public boolean saveDoctor(Doctor doctor) throws DoctorNotFoundException {

		Doctor entity = doctorRepo.save(doctor);
		System.out.println(entity.getDocId());
		return doctorRepo.existsById(entity.getDocId());
	}

	@Override
	public boolean saveClinicalData(ClinicalDataRequest request) throws PatientNotFoundException {

		ClinicalData entity2 = new ClinicalData();

		BeanUtils.copyProperties(request, entity2);
		Patient patient = patientRepo.getById(request.getId());
		entity2.setPatient(patient);
		ClinicalData clinicalData = clinicalRepo.save(entity2);

		return clinicalRepo.existsById(clinicalData.getId());
	}

	@Override
	public boolean addAppointmentDetails(AppointmentForm appt) throws ClinicalException {

		Appointment app = new Appointment();
		app.setDateTime(appt.getDateTime());
		app.setRemark(appt.getRemark());

		Optional<Patient> isPatient = patientRepo.findById(appt.getPatient_id());
		Optional<Doctor> isDoctor = doctorRepo.findById(appt.getDoc_id());
		if (isPatient.isPresent() && isDoctor.isPresent()) {
			app.setPatient(isPatient.get());
			app.setDoctor(isDoctor.get());
		} else {
			System.out.println("Give correct input for doctor and patient");

		}
		Appointment entity3 = appointmentRepo.save(app);
		return appointmentRepo.existsById(entity3.getApid());
	}

	@Override
	public List<Patient> getPatients() {
		return patientRepo.findAll();
	}

	@Override
	public List<ClinicalData> getClinicalData(int id) {

		
		Optional<Patient> isData = patientRepo.findById(id);
		if(isData.isPresent()) {
			Patient patient = isData.get();
			return patient.getClinicalData();
		}
		return null;
	}
	
	
	

}
