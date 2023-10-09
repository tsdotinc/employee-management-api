package com.tsdotinc.employeemanagement.api.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.tsdotinc.employeemanagement.api.exception.DatabaseException;
import com.tsdotinc.employeemanagement.api.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.tsdotinc.employeemanagement.api.exception.EmployeeNotFoundException;
import com.tsdotinc.employeemanagement.api.model.Employee;
import com.tsdotinc.employeemanagement.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private MessageSource messageSource;

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll() ;
	}

	public Employee findEmployeeById(Long id) {
		if (id == null || id <= 0) {
			throw new ValidationException("Invalid employee ID: " + id);
		}

		try {
			Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(id);

			if (optionalEmployee.isPresent()) {
				return optionalEmployee.get();
			} else {
				throw new EmployeeNotFoundException("Employee not found with ID: " + id);
			}
		} catch (Exception ex) {
			throw new DatabaseException("Database error occurred: " + ex.getMessage());
		}
	}

}
