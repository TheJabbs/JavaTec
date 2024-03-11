package com.example.posjohonnyjavatecspring2023;

public class ObjectFood {
    private int foodId;
    private double price;
    private String foodName, url;
    private char foodCategory;
    private String[] ingrediants;

    public ObjectFood(int foodId, String foodName, double price
    , char foodCategory, String [] ingrediants, String url) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.ingrediants = new String[ingrediants.length];
        this.foodCategory = foodCategory;

        System.arraycopy(ingrediants, 0, this.ingrediants, 0, ingrediants.length);

        this.url = url;
    }

    public ObjectFood(ObjectFood food){
        foodId = food.foodId;
        price = food.price;
        foodName = food.foodName;
        url = food.url;
        ingrediants = food.ingrediants;
    }

    //here are the getters
    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }



    public double getPrice() {
        return price;
    }

    public String[] getIngrediants() {
        return ingrediants;
    }

    public String getUrl() {
        return url;
    }

    public char getFoodCategory() {
        return foodCategory;
    }

    //here are the setters

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngrediants(String[] ingrediants) {
        this.ingrediants = ingrediants;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFoodCategory(char foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public String toString() {
        String ingrediants = "";
        for (String ingrediant : this.ingrediants) {
            ingrediants += ingrediant + ", ";
        }
        return "ObjectFood{" +
                "foodId=" + foodId +
                ", price=" + price +
                ", foodName='" + foodName + '\'' +
                ", url='" + url + '\'' +
                ", foodCategory=" + foodCategory +
                ", ingrediants=" + ingrediants +
                '}';
    }
}
