package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "pos_db")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_id")
    private int employeeId;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "employee_fname")
    private String employeeFname;
    @Basic
    @Column(name = "employee_lname")
    private String employeeLname;
    @Basic
    @Column(name = "employee_email")
    private String employeeEmail;
    @Basic
    @Column(name = "employee_phone")
    private int employeePhone;
    @Basic
    @Column(name = "employee_gender")
    private byte[] employeeGender;
    @Basic
    @Column(name = "employee_hsalary")
    private double employeeHsalary;
    @Basic
    @Column(name = "employee_status")
    private String employeeStatus;
    @Basic
    @Column(name = "employee_username")
    private String employeeUsername;
    @Basic
    @Column(name = "employee_password")
    private String employeePassword;
    @Basic
    @Column(name = "employee_dob")
    private Date employeeDob;
    @Basic
    @Column(name = "employee_fire")
    private Date employeeFire;
    @Basic
    @Column(name = "employee_hire")
    private Date employeeHire;


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getEmployeeFname() {
        return employeeFname;
    }

    public void setEmployeeFname(String employeeFname) {
        this.employeeFname = employeeFname;
    }

    public String getEmployeeLname() {
        return employeeLname;
    }

    public void setEmployeeLname(String employeeLname) {
        this.employeeLname = employeeLname;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(int employeePhone) {
        this.employeePhone = employeePhone;
    }

    public byte[] getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(byte[] employeeGender) {
        this.employeeGender = employeeGender;
    }

    public double getEmployeeHsalary() {
        return employeeHsalary;
    }

    public void setEmployeeHsalary(double employeeHsalary) {
        this.employeeHsalary = employeeHsalary;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Date getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(Date employeeDob) {
        this.employeeDob = employeeDob;
    }

    public Date getEmployeeFire() {
        return employeeFire;
    }

    public void setEmployeeFire(Date employeeFire) {
        this.employeeFire = employeeFire;
    }

    public Date getEmployeeHire() {
        return employeeHire;
    }

    public void setEmployeeHire(Date employeeHire) {
        this.employeeHire = employeeHire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return employeeId == that.employeeId && restaurantId == that.restaurantId && employeePhone == that.employeePhone && Double.compare(employeeHsalary, that.employeeHsalary) == 0 && Objects.equals(employeeFname, that.employeeFname) && Objects.equals(employeeLname, that.employeeLname) && Objects.equals(employeeEmail, that.employeeEmail) && Arrays.equals(employeeGender, that.employeeGender) && Objects.equals(employeeStatus, that.employeeStatus) && Objects.equals(employeeUsername, that.employeeUsername) && Objects.equals(employeePassword, that.employeePassword) && Objects.equals(employeeDob, that.employeeDob) && Objects.equals(employeeFire, that.employeeFire) && Objects.equals(employeeHire, that.employeeHire);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(employeeId, restaurantId, employeeFname, employeeLname, employeeEmail, employeePhone, employeeHsalary, employeeStatus, employeeUsername, employeePassword, employeeDob, employeeFire, employeeHire);
        result = 31 * result + Arrays.hashCode(employeeGender);
        return result;
    }

}
