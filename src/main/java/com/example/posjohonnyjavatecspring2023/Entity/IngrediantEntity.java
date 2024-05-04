package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ingrediant", schema = "pos_db")
public class IngrediantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingrediant_id")
    private int ingrediantId;
    @Basic
    @Column(name = "food_id")
    private Integer foodId;
    @Basic
    @Column(name = "ingrediant_names_id")
    private int ingrediantNamesId;
    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    private FoodEntity foodByFoodId;
    @ManyToOne
    @JoinColumn(name = "ingrediant_names_id", referencedColumnName = "ingrediant_names_id", nullable = false)
    private IngrediantNamesEntity ingrediantNamesByIngrediantNamesId;

    public int getIngrediantId() {
        return ingrediantId;
    }

    public void setIngrediantId(int ingrediantId) {
        this.ingrediantId = ingrediantId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public int getIngrediantNamesId() {
        return ingrediantNamesId;
    }

    public void setIngrediantNamesId(int ingrediantNamesId) {
        this.ingrediantNamesId = ingrediantNamesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngrediantEntity that = (IngrediantEntity) o;
        return ingrediantId == that.ingrediantId && ingrediantNamesId == that.ingrediantNamesId && Objects.equals(foodId, that.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingrediantId, foodId, ingrediantNamesId);
    }

    public FoodEntity getFoodByFoodId() {
        return foodByFoodId;
    }

    public void setFoodByFoodId(FoodEntity foodByFoodId) {
        this.foodByFoodId = foodByFoodId;
    }

    public IngrediantNamesEntity getIngrediantNamesByIngrediantNamesId() {
        return ingrediantNamesByIngrediantNamesId;
    }

    public void setIngrediantNamesByIngrediantNamesId(IngrediantNamesEntity ingrediantNamesByIngrediantNamesId) {
        this.ingrediantNamesByIngrediantNamesId = ingrediantNamesByIngrediantNamesId;
    }
}
