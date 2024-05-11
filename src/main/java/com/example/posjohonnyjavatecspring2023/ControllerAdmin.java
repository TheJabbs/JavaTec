package com.example.posjohonnyjavatecspring2023;

import com.example.posjohonnyjavatecspring2023.DTO.FoodDto;
import javafx.collections.FXCollections;
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

import java.util.ArrayList;
import java.util.List;

public class ControllerAdmin {

    @FXML
    private Button addBtn;

    @FXML
    private BorderPane bPane;

    @FXML
    private TableColumn<FoodDto, String> category;

    @FXML
    private Button delBtn;

    @FXML
    private TableColumn<FoodDto, Double> foodCost;

    @FXML
    private TableColumn<FoodDto, Integer> foodId;

    @FXML
    private TableColumn<FoodDto, String> foodName;

    @FXML
    private SplitMenuButton iCategory;

    @FXML
    private TextField iCost;

    @FXML
    private TextField iFname;

    @FXML
    private SplitMenuButton iIngrediants;

    @FXML
    private TableColumn<FoodDto, String> ingrediants;

    @FXML
    private TableView<FoodDto> menuItems;
    @FXML
    private ImageView imageFood;
    @FXML
    private Label notice, back;
    private DatabaseConnection connection = Main.getDefaultToken();
    private ApiClient apiClient = new ApiClient();

    public void initialize() {
        foodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodCost.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
        category.setCellValueFactory(foodDtoCharacterCellDataFeatures -> foodDtoCharacterCellDataFeatures.getValue().foodCategoriesProperty());
        ingrediants.setCellValueFactory(cellData -> cellData.getValue().ingrediantsProperty());

        List<FoodDto> listFood = apiClient.getAllFood(TempStorage.restaurantId);
        ObservableList<FoodDto> observableList = FXCollections.observableArrayList(listFood);

        menuItems.setItems(observableList);

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
            ObservableList<FoodDto> observableList = FXCollections.observableArrayList(apiClient.getAllFood(TempStorage.restaurantId));
            menuItems.setItems(observableList);

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
        FoodDto food = menuItems.getSelectionModel().getSelectedItem();
        if(food != null){
            connection.deleteFood(food.getFoodId());
            ObservableList<FoodDto> observableList = FXCollections.observableArrayList(apiClient.getAllFood(TempStorage.restaurantId));
            menuItems.setItems(observableList);
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
