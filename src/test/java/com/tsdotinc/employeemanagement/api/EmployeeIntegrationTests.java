package com.tsdotinc.employeemanagement.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.tsdotinc.employeemanagement.api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Use a test profile if available
class EmployeeIntegrationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
        // Verify that the application context loads successfully
        assertThat(employeeService).isNotNull();
    }

    @Test
    void testEmployeeService() {
        // Example integration test: Fetch an employee and validate
        var employee = employeeService.findEmployeeById(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Morgan");
    }
}