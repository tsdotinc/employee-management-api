package com.tsdotinc.employeemanagement.api.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="employee_tbl")
public class Employee implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;

	@Column(name="First_Name")
	private String firstName;

	@Column(name="Last_Name")
	private String lastName;

	@Column(name="Email_Id")
	private String emailId;

	@Column(name="Job_Title")
	private String jobTitle;

	@Column(name="Department_Name")
	private String departmentName;

	@Column(name="Phone")
	private String phone;

}
