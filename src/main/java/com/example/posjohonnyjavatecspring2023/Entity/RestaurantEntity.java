package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "restaurant", schema = "pos_db")
public class RestaurantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "restaurant_token")
    private String restaurantToken;
    @Basic
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Basic
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @Basic
    @Column(name = "restaurant_phone")
    private int restaurantPhone;
    @Basic
    @Column(name = "restaurant_Drates")
    private double restaurantDrates;
    @OneToMany(mappedBy = "restaurantByRestaurantId")
    private Collection<EmployeeEntity> employeesByRestaurantId;
    @OneToMany(mappedBy = "restaurantByRestaurantId")
    private Collection<FoodEntity> foodsByRestaurantId;
    @OneToMany(mappedBy = "restaurantByRestaurantId")
    private Collection<LaborEntity> laborsByRestaurantId;
    @OneToMany(mappedBy = "restaurantByRestaurantId")
    private Collection<OrdersEntity> ordersByRestaurantId;
    @ManyToOne
    @JoinColumn(name = "restaurant_token", referencedColumnName = "token")
    private TokenEntity tokenByRestaurantToken;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantToken() {
        return restaurantToken;
    }

    public void setRestaurantToken(String restaurantToken) {
        this.restaurantToken = restaurantToken;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public int getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(int restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public double getRestaurantDrates() {
        return restaurantDrates;
    }

    public void setRestaurantDrates(double restaurantDrates) {
        this.restaurantDrates = restaurantDrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return restaurantId == that.restaurantId && restaurantPhone == that.restaurantPhone && Double.compare(restaurantDrates, that.restaurantDrates) == 0 && Objects.equals(restaurantToken, that.restaurantToken) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(restaurantAddress, that.restaurantAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, restaurantToken, restaurantName, restaurantAddress, restaurantPhone, restaurantDrates);
    }

    public Collection<EmployeeEntity> getEmployeesByRestaurantId() {
        return employeesByRestaurantId;
    }

    public void setEmployeesByRestaurantId(Collection<EmployeeEntity> employeesByRestaurantId) {
        this.employeesByRestaurantId = employeesByRestaurantId;
    }

    public Collection<FoodEntity> getFoodsByRestaurantId() {
        return foodsByRestaurantId;
    }

    public void setFoodsByRestaurantId(Collection<FoodEntity> foodsByRestaurantId) {
        this.foodsByRestaurantId = foodsByRestaurantId;
    }

    public Collection<LaborEntity> getLaborsByRestaurantId() {
        return laborsByRestaurantId;
    }

    public void setLaborsByRestaurantId(Collection<LaborEntity> laborsByRestaurantId) {
        this.laborsByRestaurantId = laborsByRestaurantId;
    }

    public Collection<OrdersEntity> getOrdersByRestaurantId() {
        return ordersByRestaurantId;
    }

    public void setOrdersByRestaurantId(Collection<OrdersEntity> ordersByRestaurantId) {
        this.ordersByRestaurantId = ordersByRestaurantId;
    }

    public TokenEntity getTokenByRestaurantToken() {
        return tokenByRestaurantToken;
    }

    public void setTokenByRestaurantToken(TokenEntity tokenByRestaurantToken) {
        this.tokenByRestaurantToken = tokenByRestaurantToken;
    }
}
