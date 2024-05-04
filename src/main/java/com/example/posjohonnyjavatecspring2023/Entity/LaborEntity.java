package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "labor", schema = "pos_db")
public class LaborEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "labor_id")
    private int laborId;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "employee_id")
    private int employeeId;
    @Basic
    @Column(name = "labor_start")
    private Timestamp laborStart;
    @Basic
    @Column(name = "labor_end")
    private Timestamp laborEnd;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurantByRestaurantId;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    private EmployeeEntity employeeByEmployeeId;

    public int getLaborId() {
        return laborId;
    }

    public void setLaborId(int laborId) {
        this.laborId = laborId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getLaborStart() {
        return laborStart;
    }

    public void setLaborStart(Timestamp laborStart) {
        this.laborStart = laborStart;
    }

    public Timestamp getLaborEnd() {
        return laborEnd;
    }

    public void setLaborEnd(Timestamp laborEnd) {
        this.laborEnd = laborEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborEntity that = (LaborEntity) o;
        return laborId == that.laborId && restaurantId == that.restaurantId && employeeId == that.employeeId && Objects.equals(laborStart, that.laborStart) && Objects.equals(laborEnd, that.laborEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborId, restaurantId, employeeId, laborStart, laborEnd);
    }

    public RestaurantEntity getRestaurantByRestaurantId() {
        return restaurantByRestaurantId;
    }

    public void setRestaurantByRestaurantId(RestaurantEntity restaurantByRestaurantId) {
        this.restaurantByRestaurantId = restaurantByRestaurantId;
    }

    public EmployeeEntity getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}
