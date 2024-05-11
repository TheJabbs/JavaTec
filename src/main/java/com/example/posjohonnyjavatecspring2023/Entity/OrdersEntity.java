package com.example.posjohonnyjavatecspring2023.Entity;

import com.example.posjohonnyjavatecspring2023.ApiClient;
import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "pos_db")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "order_number")
    private int orderNumber;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "employee_id")
    private int employeeId;
    @Basic
    @Column(name = "food_id")
    private int foodId;
    @Basic
    @Column(name = "order_date")
    private Timestamp orderDate;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurantByRestaurantId;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    private EmployeeEntity employeeByEmployeeId;
    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    private FoodEntity foodByFoodId;

    public OrdersEntity() {
    }

    public OrdersEntity(int restaurantId, int employeeId, int foodId) {
        this.restaurantId = restaurantId;
        this.employeeId = employeeId;
        this.foodId = foodId;
    }

    public StringProperty getFoodNameProperty() {
        return new SimpleStringProperty(getFoodNameById());
    }

    public String getFoodNameById() {
        return new ApiClient().getFoodNameById(foodId);
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && orderNumber == that.orderNumber && restaurantId == that.restaurantId && employeeId == that.employeeId && foodId == that.foodId && Objects.equals(orderDate, that.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderNumber, restaurantId, employeeId, foodId, orderDate);
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

    public FoodEntity getFoodByFoodId() {
        return foodByFoodId;
    }

    public void setFoodByFoodId(FoodEntity foodByFoodId) {
        this.foodByFoodId = foodByFoodId;
    }
}
