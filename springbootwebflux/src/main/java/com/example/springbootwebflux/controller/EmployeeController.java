package com.example.springbootwebflux.controller;


import com.example.springbootwebflux.model.Employee;
import com.example.springbootwebflux.security.CustomPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping(value = "/api/employees")
    public Employee getEmployee(@AuthenticationPrincipal CustomPrincipal customPrincipal)  {
        return new Employee();
    }
}
