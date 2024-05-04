package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "food_category", schema = "pos_db")
public class FoodCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "food_category_id")
    private int foodCategoryId;
    @Basic
    @Column(name = "food_id")
    private int foodId;
    @Basic
    @Column(name = "food_category_category")
    private String foodCategoryCategory;
    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    private FoodEntity foodByFoodId;

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodCategoryCategory() {
        return foodCategoryCategory;
    }

    public void setFoodCategoryCategory(String foodCategoryCategory) {
        this.foodCategoryCategory = foodCategoryCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodCategoryEntity that = (FoodCategoryEntity) o;
        return foodCategoryId == that.foodCategoryId && foodId == that.foodId && Objects.equals(foodCategoryCategory, that.foodCategoryCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodCategoryId, foodId, foodCategoryCategory);
    }

    public FoodEntity getFoodByFoodId() {
        return foodByFoodId;
    }

    public void setFoodByFoodId(FoodEntity foodByFoodId) {
        this.foodByFoodId = foodByFoodId;
    }
}
