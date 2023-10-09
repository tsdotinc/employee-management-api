package com.tsdotinc.employeemanagement.api.service;

import com.tsdotinc.employeemanagement.api.config.MessageSourceConfig;
import com.tsdotinc.employeemanagement.api.exception.DatabaseException;
import com.tsdotinc.employeemanagement.api.exception.ValidationException;
import com.tsdotinc.employeemanagement.api.model.Employee;
import com.tsdotinc.employeemanagement.api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = {
        "classpath:application.properties",
        "classpath:database-error-messages.properties",
        "classpath:validation-error-messages.properties"
})
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy //Use spy to partially mock the service
    private EmployeeService employeeServiceSpy;

    @Mock
    private MessageSource messageSource;

    @Test
    public void testFindEmployeeById_Success(){
        //Create a sample Employee Object
        Employee sampleEmployee = new Employee();
        sampleEmployee.setId(1L);
        sampleEmployee.setFirstName("John");

        //Mock the behavior of the employeeRepository to return the sampleEmployee when findById is called
        when(employeeRepository.findEmployeeById(1L)).thenReturn(Optional.of(sampleEmployee));

        //Call the service method to find the employee by ID
        Employee result = employeeService.findEmployeeById(1L);

        //Assert that the result matches the sampleEmployee
        assertEquals(sampleEmployee, result);
    }

    @Test
    public void testFindEmployeeById_NotFound(){
        //Mock the behavior of employeeRepository to return an empty Optional when findById is called with id 2L.
        lenient().when(employeeRepository.findEmployeeById(2L)).thenReturn(Optional.empty());

        //Configure the spy to throw a DatabaseException when no employee is found
        doThrow(DatabaseException.class).when(employeeServiceSpy).findEmployeeById(2L);

        //Use assertThrows to verify that a DatabaseException is thrown with the expected error message
        assertThrows(DatabaseException.class,() ->{
            employeeServiceSpy.findEmployeeById(2L);
       });
    }

    @Test
    public void testFindEmployeeById_InvalidInput(){
        //Test when an invalid employee ID (null) is provided
        Long invalidId = null;
        assertThrows(ValidationException.class, () ->{
          employeeService.findEmployeeById(invalidId);
        });

        //Test when an invalid employee ID (zero) is provided
        Long zeroId = 0L;
        assertThrows(ValidationException.class, () ->{
            employeeService.findEmployeeById(zeroId);
        });
    }

    @Test
    public void testFindEmployeeById_DatabaseError(){
        //Mock the behavior of employeeRepository to throw a database exception
        when(employeeRepository.findEmployeeById(3L)).thenThrow(new DatabaseException("Database Error"));

        //Test when a database exception occurs
        assertThrows(DatabaseException.class, () ->{
            employeeService.findEmployeeById(3L);
        });
    }

    @Test
    public void testFindEmployeeById_OtherException(){
        //Mock the behavior of employeeRepository to throw custom exception(e.g., DatabaseException)
        when(employeeRepository.findEmployeeById(4L));

        //Test when an unexpected exception occurs (e.g., DatabaseException)
        assertThrows(DatabaseException.class, () ->{
            employeeService.findEmployeeById(4L);
        });
    }
}
