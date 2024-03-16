package com.example.posjohonnyjavatecspring2023;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
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
    @FXML
    private ImageView imageFood;
    @FXML
    private Label notice, back;
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

    @FXML
    public void imageViewDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasImage() || db.hasFiles()) {
            imageFood.setImage(new Image(db.getFiles().get(0).toURI().toString()));
        }
    }

    @FXML
    public void imageViewDragOver(DragEvent event) {

        if (event.getDragboard().hasImage() || event.getDragboard().hasFiles()) {
            System.out.println("Drag over");
            event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
        }

        event.consume();
    }

    @FXML
    public void onClickAdd() throws InterruptedException {
        ArrayList<String> ingrediants = new ArrayList<>();
        ArrayList<String> categorie = new ArrayList<>();
        for (MenuItem item : iIngrediants.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if (checkMenuItem.isSelected()) {
                ingrediants.add(checkMenuItem.getText());
            }
        }
        for (MenuItem item : iCategory.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if (checkMenuItem.isSelected()) {
                categorie.add(checkMenuItem.getText());
            }
        }
        Double price = 0.0;
        try {
            price = Double.parseDouble(iCost.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!iFname.getText().isEmpty() && !categorie.isEmpty() && price > 0) {
            connection.addFood(iFname.getText(), price, ingrediants, categorie);
            ObservableList foods = connection.getAllFood();
            menuItems.setItems(foods);

            System.out.println("Success");
            clearEntry();
            notice.setText("Operation Successful");
            Thread.sleep(3000);
            notice.setText("");
            return;
        }

        notice.setText("Operation Failed");
        Thread.sleep(3000);
        notice.setText("");

    }

    public void clearEntry() {
        iFname.setText("");
        iCost.setText("");
        iIngrediants.getItems().clear();
        iCategory.getItems().clear();
    }

    @FXML
    public void onDelClicked(){
        ObjectFood food = menuItems.getSelectionModel().getSelectedItem();
        if(food != null){
            connection.deleteFood(food.getFoodId());
            ObservableList foods = connection.getAllFood();
            menuItems.setItems(foods);
        }
    }

    @FXML
    public void onBackClicked(){
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard-view.fxml"));
        try {
            stage.getScene().setRoot(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
