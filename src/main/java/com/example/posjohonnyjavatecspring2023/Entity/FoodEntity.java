package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "food", schema = "pos_db")
public class FoodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "food_id")
    private int foodId;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "food_name")
    private String foodName;
    @Basic
    @Column(name = "food_price")
    private double foodPrice;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantEntity restaurantByRestaurantId;
    @OneToMany(mappedBy = "foodByFoodId")
    private Collection<FoodCategoryEntity> foodCategoriesByFoodId;
    @OneToMany(mappedBy = "foodByFoodId")
    private Collection<IngrediantEntity> ingrediantsByFoodId;
    @OneToMany(mappedBy = "foodByFoodId")
    private Collection<OrdersEntity> ordersByFoodId;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEntity that = (FoodEntity) o;
        return foodId == that.foodId && restaurantId == that.restaurantId && Double.compare(foodPrice, that.foodPrice) == 0 && Objects.equals(foodName, that.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, restaurantId, foodName, foodPrice);
    }

    public RestaurantEntity getRestaurantByRestaurantId() {
        return restaurantByRestaurantId;
    }

    public void setRestaurantByRestaurantId(RestaurantEntity restaurantByRestaurantId) {
        this.restaurantByRestaurantId = restaurantByRestaurantId;
    }

    public Collection<FoodCategoryEntity> getFoodCategoriesByFoodId() {
        return foodCategoriesByFoodId;
    }

    public void setFoodCategoriesByFoodId(Collection<FoodCategoryEntity> foodCategoriesByFoodId) {
        this.foodCategoriesByFoodId = foodCategoriesByFoodId;
    }

    public Collection<IngrediantEntity> getIngrediantsByFoodId() {
        return ingrediantsByFoodId;
    }

    public void setIngrediantsByFoodId(Collection<IngrediantEntity> ingrediantsByFoodId) {
        this.ingrediantsByFoodId = ingrediantsByFoodId;
    }

    public Collection<OrdersEntity> getOrdersByFoodId() {
        return ordersByFoodId;
    }

    public void setOrdersByFoodId(Collection<OrdersEntity> ordersByFoodId) {
        this.ordersByFoodId = ordersByFoodId;
    }
}
