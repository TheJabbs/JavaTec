package com.example.posjohonnyjavatecspring2023;

public class ObjectOrderList {
    private String itemName;
    private int id, orderId;
    private String ingredients;
    private int quantity;

    //default constructor

    public ObjectOrderList() {
    }
    public ObjectOrderList(ObjectOrder order, int orderId) {
        this.id = order.getId();
        this.orderId = orderId;
        this.itemName = order.getFoodName();
        this.quantity = order.getQuantity();
        String ingredients = "";
        for(int i = 0; i < order.getIngrediants().size(); i++){
            ingredients += order.getIngrediants().get(i) + ", ";
        }

        this.ingredients = ingredients;
    }

    public String getItemName() {
        return itemName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Item: " + itemName + " Ingredients: " + ingredients + " Quantity: " + quantity;
    }


}
