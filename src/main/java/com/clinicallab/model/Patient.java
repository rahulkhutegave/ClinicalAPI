package com.clinicallab.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String lastname;
	private String firstname;
	private int age;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
	private Appointment appointment; 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
	private List<ClinicalData> clinicalData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<ClinicalData> getClinicalData() {
		return clinicalData;
	}

	public void setClinicalData(List<ClinicalData> clinicalData) {
		this.clinicalData = clinicalData;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	

}
