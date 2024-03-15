package com.example.posjohonnyjavatecspring2023;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class ControllerIngrediants {

    @FXML
    private Button add;

    @FXML
    private FlowPane content;

    @FXML
    private ImageView food;

    @FXML
    private Label identifier;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private Label costS;
    @FXML
    private Button cancel;
    @FXML
    private Label foodId;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ControllerCashier cashierC;

    public void initialize() {
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    public void setIngrediantList(ObjectFood food){
        identifier.setText(food.getFoodName());
        foodId.setText(food.getFoodId() + "");
        try {
            this.food.setImage(new Image(food.getUrl()).isError() ? new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-0.png", 168, 89, false, true) : new Image(food.getUrl(), 168, 89, false, true));
        } catch (Exception e) {
            this.food.setImage(new Image("com/example/posjohonnyjavatecspring2023/Image Food/image-1.png", 168, 89, false, true));
        }
        costS.setText(food.getPrice() + " $");
        System.out.println(food.getIngrediants().length);
        for (String ingrediant : food.getIngrediants()) {
            CheckBox checkBox = new CheckBox(ingrediant);

            checkBox.setScaleX(1);
            checkBox.setScaleY(1);
            checkBox.setPadding(new javafx.geometry.Insets(0, 5, 0, 0));
            checkBox.setSelected(true);

            checkBoxes.add(checkBox);
            content.getChildren().add(checkBox);
        }
    }

    @FXML
    public void buttonClick(){
        double costL = Double.parseDouble(costS.getText().substring(0, costS.getText().length() - 2));
        int orderId = Integer.parseInt(foodId.getText());
        ArrayList<String> checked = new ArrayList<>();
        for(int i = 0; i < checkBoxes.size(); i++){
            if(checkBoxes.get(i).isSelected()){
                checked.add(checkBoxes.get(i).getText());
            }
        }
        cashierC.getOrderList().add((new ObjectOrder(identifier.getText(), orderId ,quantity.getValue(), costL, checked)));
        System.out.print(cashierC.getOrderList().toString());
        cashierC.updateTable();

        cancel();
    }
    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }
    public void setCashierC(ControllerCashier cashierC) {
        this.cashierC = cashierC;
    }


    @FXML
    public void cancel(){
        cashierC.getbPane().setRight(null);
    }

}
