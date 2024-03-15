package com.example.posjohonnyjavatecspring2023;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ControllerAdmin {

    @FXML
    private Button addBtn;

    @FXML
    private BorderPane bPane;

    @FXML
    private TableColumn<ObjectFood, String> category;

    @FXML
    private Button delBtn;

    @FXML
    private TableColumn<ObjectFood, Double> foodCost;

    @FXML
    private TableColumn<ObjectFood, Integer> foodId;

    @FXML
    private TableColumn<ObjectFood, String> foodName;

    @FXML
    private SplitMenuButton iCategory;

    @FXML
    private TextField iCost;

    @FXML
    private TextField iFname;

    @FXML
    private SplitMenuButton iIngrediants;

    @FXML
    private TableColumn<ObjectFood, String> ingrediants;

    @FXML
    private TableView<ObjectFood> menuItems;
    private DatabaseConnection connection = Main.getDefaultToken();

    public void initialize() {
        foodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("foodCategory"));
        ingrediants.setCellValueFactory(cellData -> cellData.getValue().ingrediantsProperty());

        ObservableList foods = connection.getAllFood();
        menuItems.setItems(foods);

        iIngrediants.getItems().addAll(connection.getAllIngrediantNames());

    }

}
