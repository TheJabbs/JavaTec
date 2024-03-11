package com.example.posjohonnyjavatecspring2023;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ControllerWidget {
    @FXML
    private Label fname;

    @FXML
    private ImageView image;

    @FXML
    private Label price;
    @FXML
    private AnchorPane widget;

    private ObjectFood food;
    ControllerCashier cashierC;

    public void initialize() {

    }

    public void setWidget(ObjectFood food) {
        fname.setText(food.getFoodName());
        try {
            image.setImage(new Image(food.getUrl()).isError() ? new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-0.png", 168, 89, false, true) : new Image(food.getUrl(), 168, 89, false, true));
        } catch (Exception e) {
            image.setImage(new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-1.png", 168, 89, false, true));
        }
        price.setText(food.getPrice() + " $");
        this.food = food;
    }

    @FXML
    public void topping() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ingrediants.fxml"));
            AnchorPane pane = loader.load();
            ControllerIngrediants controller = loader.getController();
            controller.setCashierC(cashierC);
            controller.setIngrediantList(food);

            cashierC.getbPane().setRight(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCashierC(ControllerCashier cashierC) {
        this.cashierC = cashierC;
    }


}
