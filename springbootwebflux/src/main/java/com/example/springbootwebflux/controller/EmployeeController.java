package com.example.springbootwebflux.controller;


import com.example.springbootwebflux.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping(value = "/api/employees")
    public Employee getEmployee(@RequestHeader("x-user-id") String mobile)  {
        return new Employee();
    }
}
