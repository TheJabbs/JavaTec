package com.example.posjohonnyjavatecspring2023;


import com.example.posjohonnyjavatecspring2023.DTO.FoodDto;
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

    private FoodDto food;
    ControllerCashier cashierC;

    public void initialize() {

    }

    public void setWidget(FoodDto food) {
        fname.setText(food.getFoodName());
        try {
            image.setImage(new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-"+food.getFoodId()+".png", 168, 89, false, true));
        } catch (Exception e) {
            image.setImage(new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-0.png", 168, 89, false, true));
        }
        price.setText(food.getFoodPrice() + " $");
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
