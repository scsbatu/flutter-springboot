package com.example.springboot.api;

import com.example.springboot.dto.Employee;
import com.example.springboot.security.CustomPrincipal;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class EmployeeController {
    @GetMapping(value = "/api/employees")
    public Employee getEmployee(@AuthenticationPrincipal CustomPrincipal customPrincipal)  {
        System.out.println(customPrincipal); //POC that the user is actually logged and we have his information on session
        return new Employee();
    }
}
