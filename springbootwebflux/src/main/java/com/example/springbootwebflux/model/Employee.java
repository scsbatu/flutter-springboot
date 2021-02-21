package com.example.springbootwebflux.model;

import lombok.Data;

@Data
public class Employee {
    private int id = 1000000;
    private String name = "Suraj Batuwana";
    private String employeeNumber = "EMP0111111111";
    private String country = "Australia";
}