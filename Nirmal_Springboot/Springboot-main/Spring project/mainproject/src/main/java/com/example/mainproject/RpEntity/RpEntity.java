package com.example.mainproject.RpEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Students")
public class RpEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="S_no")
	private int student_no;
	
	@Column(name="S_name")
	private String student_name;
	
	@Column(name="S_dept")
	private String department;
	
	@Column(name="Location")
	private String  location;

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
