package com.tsdotinc.employeemanagement.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="employee_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1954355911137869829L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
