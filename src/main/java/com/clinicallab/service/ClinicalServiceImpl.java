package com.clinicallab.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import com.clinicallab.utils.EmailUtils;

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
		Patient patient = patientRepo.getById(request.getPatientid());
		float height = request.getHeight();
		int weight = request.getWeight();

		float bmi = weight / (height * height);
		entity2.setBmi(bmi);

		entity2.setPatient(patient);
		ClinicalData clinicalData = clinicalRepo.save(entity2);

		return clinicalRepo.existsById(clinicalData.getId());
	}

	@Override
	public boolean addAppointmentDetails(AppointmentForm appt) throws Exception {

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
			throw new ClinicalException("Give correct Input");
		}
		Appointment entity3 = appointmentRepo.save(app);
		
		
		String message= "Hi!! \n "+"\n"
				+ "Your appointment is confirmed. Please arrive 10 minutes early before time \n"
				+ "Appointment Time : "+entity3.getDateTime() +"\n"+"\n"
				+"Thank You";
		
		
		EmailUtils.sendEmail(message, "Appointment Confirmation", entity3.getPatient().getEmail(), "fromEmail@gmail.com");
		
		return appointmentRepo.existsById(entity3.getApid());
	}

	@Override
	public List<Patient> getPatients(int pageNo, int size) {
		PageRequest pageRequest = PageRequest.of(pageNo, size);
		Page<Patient> page = patientRepo.findAll(pageRequest);
		return page.getContent();
	}

	@Override
	public List<ClinicalData> getClinicalData(int id) throws PatientNotFoundException {

		Optional<Patient> isData = patientRepo.findById(id);
		if (isData.isPresent()) {
			Patient patient = isData.get();
			return patient.getClinicalData();
		} else {
			throw new PatientNotFoundException("Patient NOt Found");
		}

	}

	@Override
	public List<Patient> sortPatientsByAge(int pageNo) {
		int size = 5;
		PageRequest pageRequest = PageRequest.of(pageNo, size, Direction.DESC, "age");
		Page<Patient> page = patientRepo.findAll(pageRequest);
		return page.getContent();
	}

	@Override
	public String emailCheck(String email, String regexPattern) {

		boolean istrue = Pattern.compile(regexPattern).matcher(email).matches();
		if (istrue) {

			Patient patient = new Patient();
			patient.setEmail(email.trim());

			Example<Patient> example = Example.of(patient);
			Optional<Patient> isPatient = patientRepo.findOne(example);
			if (isPatient.isPresent()) {
				return "Duplicate Email ID";
			} else {
				return "Email is Valid";
			}

		} else {
			return "Invalid Email Address";
		}

	}

	

}
