package com.example.posjohonnyjavatecspring2023;

import com.example.posjohonnyjavatecspring2023.DTO.FoodDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.posjohonnyjavatecspring2023.Main.getDefaultToken;

public class ControllerCashier {
    @FXML
    private BorderPane bPane;
    @FXML
    private TableView<ObjectOrder> receipTable;
    @FXML
    private TableColumn<ObjectOrder, Integer> Q;
    @FXML
    private TableColumn<ObjectOrder, String> itemName;
    @FXML
    private TableColumn<ObjectOrder, Double> unitPrice, total;

    @FXML
    private Label totalPL;
    @FXML
    private Label totalPD;

    @FXML
    private GridPane gridB;

    @FXML
    private GridPane gridC;

    @FXML
    private GridPane gridD;

    @FXML
    private GridPane gridOffers;
    @FXML
    private TextField payedD;

    @FXML
    private TextField payedL;
    @FXML
    private Label returnL, returnD;

    private final DatabaseConnection connection = getDefaultToken();
    ObservableList<FoodDto> foodL = FXCollections.observableArrayList();
    ObservableList<ObjectOrder> orders = FXCollections.observableArrayList();


    public void initialize() {
        itemName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        Q.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        showWidget();


    }

    private void showWidget() {

        foodL = FXCollections.observableArrayList(new ApiClient().getAllFood(TempStorage.restaurantId));
        int[][] insert = new int[2][4];

        gridB.getRowConstraints().clear();
        gridB.getColumnConstraints().clear();
        gridC.getRowConstraints().clear();
        gridC.getColumnConstraints().clear();
        gridD.getRowConstraints().clear();
        gridD.getColumnConstraints().clear();
        gridOffers.getRowConstraints().clear();
        gridOffers.getColumnConstraints().clear();

        for (int i = 0; i < foodL.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Widget-view.fxml"));
                Node fxmlNode = loader.load();
                ControllerWidget controller = loader.getController();
                controller.setCashierC(this);
                FoodDto food = foodL.get(i);
                controller.setWidget(food);

                if (food.getFoodCategories().get(0) == 's') {
                    gridOffers.add(fxmlNode, insert[0][0]++, insert[1][0]);
                } else if (food.getFoodCategories().get(0) == 'd') {
                    gridD.add(fxmlNode, insert[0][1]++, insert[1][1]);
                } else if (food.getFoodCategories().get(0) == 'm') {
                    gridB.add(fxmlNode, insert[0][2]++, insert[1][2]);
                } else if (food.getFoodCategories().get(0) == 'c') {
                    gridC.add(fxmlNode, insert[0][3]++, insert[1][3]);
                } else {
                    System.out.println("The food category is not valid");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void calculateTotal() {
        double total = 0;
        for (ObjectOrder order : receipTable.getItems()) {
            total += order.getTotal();
        }
        totalPD.setText("" + total);
        totalPL.setText("" + total * getDefaultToken().getdRate());
    }

    public void updateTable(){
        receipTable.setItems(orders);
        calculateTotal();
    }

    @FXML
    public void displayChange(){
        double payedD = this.payedD.getText().isEmpty() ? 0 : (Double.parseDouble(this.payedD.getText().trim()));
        double payedL = this.payedL.getText().isEmpty() ? 0 : (Double.parseDouble(this.payedL.getText().trim()));

        returnD.setText("0");
        returnD.setText("0");

        double totalPD = Double.parseDouble(this.totalPD.getText());

        double change = (payedD + payedL/getDefaultToken().getdRate()) - totalPD;
        double rate = getDefaultToken().getdRate();

        if(change < 0){
            returnD.setText("-1");
            returnL.setText("-1");
        }else if(payedD > payedL){
            returnD.setText((change - (change % 5)) + "");
            returnL.setText(((change % 5) * rate) + "");
        } else if (payedL >= (payedD * rate)) {
            returnL.setText("" + (change * getDefaultToken().getdRate()));
        }


    }

    @FXML
    public void clearTable(){
        orders = FXCollections.observableArrayList();
        returnD.setText("0");
        returnL.setText("0");

        payedL.setText("0.0");
        payedD.setText("0.0");
        updateTable();
    }

    @FXML
    public void payClicked(){
        if(!returnD.getText().equals("-1") || !returnL.getText().equals("-1")){
            for (ObjectOrder order : orders) {
                connection.insertOrder(order);
            }
            clearTable();
        }

    }

    @FXML
    public void deleteSelected(){
        orders.remove(receipTable.getSelectionModel().getSelectedItem());
        updateTable();
    }
    @FXML
    public void onBackClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard-view.fxml"));
            bPane.getScene().setRoot(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ObjectOrder> getOrderList() {
        return orders;
    }
    public BorderPane getbPane() {
        return bPane;
    }

    @FXML
    public void onScroll(ScrollEvent event) {
        double deltaY = event.getDeltaY();
        double deltaX = event.getDeltaX();
        ScrollPane scrollPane = (ScrollPane) event.getSource();

        scrollPane.setHvalue(scrollPane.getHvalue() - deltaY / scrollPane.getContent().getBoundsInLocal().getWidth());
        scrollPane.setVvalue(scrollPane.getVvalue() - deltaX / scrollPane.getContent().getBoundsInLocal().getHeight());

        event.consume();
    }

}


