package com.example.posjohonnyjavatecspring2023;

import java.util.ArrayList;

public class ObjectOrder {
    private String foodName;
    private int quantity, id;
    private double price, total;
    private ArrayList<String> ingrediants;

    public ObjectOrder() {
        ingrediants = new ArrayList<>();
    }
    public ObjectOrder(String foodName, int id,int quantity, double price) {
        ingrediants = new ArrayList<>();
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
        this.id = id;
    }

    public ObjectOrder(String foodName,int id, int quantity, double price, ArrayList<String> ingrediants) {
        this(foodName,id, quantity, price);
        setIngrediants(ingrediants);
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        total = this.quantity * price;
    }

    public void setPrice(double price) {
        this.price = price;
        total = this.quantity * this.price;
    }

    public void setIngrediants(ArrayList<String> ingrediants) {
        this.ingrediants = ingrediants;
    }

    public ArrayList<String> getIngrediants() {
        return ingrediants;
    }


    @Override
    public String toString() {
        return "ObjectOrder{" +
                "foodName='" + foodName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
