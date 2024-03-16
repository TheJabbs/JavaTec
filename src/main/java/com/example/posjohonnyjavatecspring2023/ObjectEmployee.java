package com.example.posjohonnyjavatecspring2023;

import java.util.Date;

public class ObjectEmployee {
    private int employeeId;
    private String employeeFname;
    private String employeeLname;
    private char employeeStatus;

    // Constructors
    public ObjectEmployee() {
        // Default constructor
    }

    public ObjectEmployee(int employeeId, String employeeFname, String employeeLname, char employeeStatus) {
        this.employeeId = employeeId;
        this.employeeFname = employeeFname;
        this.employeeLname = employeeLname;
        this.employeeStatus = employeeStatus;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFname() {
        return employeeFname;
    }

    public String getEmployeeLname() {
        return employeeLname;
    }

    public char getEmployeeStatus() {
        return employeeStatus;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeFname(String employeeFname) {
        this.employeeFname = employeeFname;
    }

    public void setEmployeeLname(String employeeLname) {
        this.employeeLname = employeeLname;
    }

    public void setEmployeeStatus(char employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

}
