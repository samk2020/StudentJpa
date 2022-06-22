package com.cognixia.jump.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity -> define the class that is used to map out the table in SQL
//		   -> let JPA know that this class should match a table in the db named "student"
//		   -> if no table with name "student", JPA will create that table

@Entity
public class Student {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;

	// @Column -> provide definitions for how we want to set up the column
	// name -> change name of column
	@Column(name="fname")
	private String firstName;

	@Column(name="lname")
	private String lastName;

	// unique = true ==> has a unique constraint
	// nullable = false ==> NOT NULL constraint
	@Column(unique = true, nullable = false)
	private String email;

	private Double gpa;

	private LocalDate dob;

	@Column( columnDefinition = "varchar(100) DEFAULT 'Undecided' " )
	private String major;

	
	public Student() {

	}

	public Student(Integer id, String firstName, String lastName, String email, Double gpa, LocalDate dob,
			String major) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gpa = gpa;
		this.dob = dob;
		this.major = major;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getGpa() {
		return gpa;
	}

	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", gpa=" + gpa + ", dob=" + dob + ", major=" + major + "]";
	}

}
