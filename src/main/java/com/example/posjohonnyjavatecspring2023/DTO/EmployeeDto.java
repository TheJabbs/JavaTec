package com.example.posjohonnyjavatecspring2023.DTO;

public class EmployeeDto {

    private String employeePassword, employeeUsername;

    public EmployeeDto() {
        // Default constructor
    }

    public EmployeeDto( String employeeUsername, String employeePassword) {
        this.employeePassword = employeePassword;
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

}
