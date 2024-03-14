package com.example.posjohonnyjavatecspring2023;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.posjohonnyjavatecspring2023.Main.getDefaultToken;

public class ControllerKitchen {

    @FXML
    private TableColumn<ObjectOrderList, String> aIngrediants;
    @FXML
    private TableColumn<ObjectOrderList, Integer> aId;
    @FXML
    private TableColumn<ObjectOrderList, Integer> aOId;
    @FXML
    private TableColumn<ObjectOrderList, String> aItemName;

    @FXML
    private TableColumn<ObjectOrderList, Double> aQuantity;

    @FXML
    private TableView<ObjectOrderList> acceptedTable;

    @FXML
    private BorderPane bPane;

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private TextField input3;

    @FXML
    private TextField input4;

    @FXML
    private TextField input5;

    @FXML
    private TextField input6;

    @FXML
    private TableColumn<ObjectOrderList, String> pIngrediants;

    @FXML
    private TableColumn<ObjectOrderList, String> pItemName;

    @FXML
    private TableColumn<ObjectOrderList, Double> pQuantity;
    @FXML
    private TableColumn<ObjectOrderList, Integer> pId;
    @FXML
    private TableColumn<ObjectOrderList, Integer> pOId;
    @FXML
    private Button clockO1;

    @FXML
    private Button clockO2;

    @FXML
    private Button clockO3;

    @FXML
    private Button clockO4;

    @FXML
    private Button clockO5;

    @FXML
    private Button clockO6;


    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private PasswordField pass3;

    @FXML
    private PasswordField pass4;

    @FXML
    private PasswordField pass5;

    @FXML
    private PasswordField pass6;

    @FXML
    private TableView<ObjectOrderList> pendingTable;
    @FXML
    private Button clockIn1,clockIn2,clockIn3,clockIn4,clockIn5,clockIn6;

    private ObservableList<ObjectOrderList> pending = FXCollections.observableArrayList();
    private ObservableList<ObjectOrderList> accepted = FXCollections.observableArrayList();
    private DatabaseConnection connection = getDefaultToken();
    private int employeeIds[] = new int[6];

    @FXML
    void initialize() {
        pItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        pQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pIngrediants.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        pId.setCellValueFactory(new PropertyValueFactory<>("id"));
        pOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        aItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        aQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        aIngrediants.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        aId.setCellValueFactory(new PropertyValueFactory<>("id"));
        aOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        getPendingOrders();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> getPendingOrders()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    private void getPendingOrders() {
        ObservableList<ObjectOrderList> pendingReceived = connection.getAllOrderList();
        if(pending.size() != pendingReceived.size()) {
            pending = pendingReceived;
            pendingTable.setItems(pending);
            System.out.println("Updated");
        }

    }

    @FXML
    private void onAccept() {
        ObjectOrderList order = pendingTable.getSelectionModel().getSelectedItem();
        if (order != null) {
            pending.remove(order);
            accepted.add(order);
            connection.deleteOrderList(order.getOrderId());
            acceptedTable.setItems(accepted);
        }
    }

    @FXML
    private void onFinish() {
        ObjectOrderList order = acceptedTable.getSelectionModel().getSelectedItem();
        if (order != null) {
            accepted.remove(order);
            acceptedTable.setItems(accepted);
        }
    }

    @FXML
    private void onClockInClicked(ActionEvent event) {
        Button clickedBtn = (Button) event.getSource();
        System.out.print(clickedBtn.getId() + " ");
        clickedBtn.setDisable(true);
        if (clickedBtn.getId().equals("clockIn1")) {
            employeeIds[0] = connection.clockIn(input1.getText(), pass1.getText());
            if (employeeIds[0] != 0) {
                input1.setDisable(true);
                pass1.setDisable(true);
                clockO1.setDisable(false);
            }
        } else if (clickedBtn.getId().equals("clockIn2")) {
            employeeIds[1] = connection.clockIn(input2.getText(), pass2.getText());
            if(employeeIds[1] != 0){
                input2.setDisable(true);
                pass2.setDisable(true);
                clockO2.setDisable(false);
            }
        } else if (clickedBtn.getId().equals("clockIn3")) {
            employeeIds[2] = connection.clockIn(input3.getText(), pass3.getText());
            if(employeeIds[2] != 0){
                input3.setDisable(true);
                pass3.setDisable(true);
                clockO3.setDisable(false);
            }
        } else if (clickedBtn.getId().equals("clockIn4")) {
            employeeIds[3] = connection.clockIn(input4.getText(), pass4.getText());
            if(employeeIds[3] != 0){
                input4.setDisable(true);
                pass4.setDisable(true);
                clockO4.setDisable(false);
            }
        } else if (clickedBtn.getId().equals("clockIn5")) {
            employeeIds[4] = connection.clockIn(input5.getText(), pass5.getText());
            if(employeeIds[4] != 0){
                input5.setDisable(true);
                pass5.setDisable(true);
                clockO5.setDisable(false);
            }
        } else if (clickedBtn.getId().equals("clockIn6")) {
            employeeIds[5] = connection.clockIn(input6.getText(), pass6.getText());
            if(employeeIds[5] != 0){
                input6.setDisable(true);
                pass6.setDisable(true);
                clockO6.setDisable(false);
            }
        }
    }

    @FXML
    private void onClockOutClicked(ActionEvent event) {
        Button clickedBtn = (Button) event.getSource();
        System.out.print(clickedBtn.getId() + " ");
        clickedBtn.setDisable(true);
        if (clickedBtn.getId().equals("clockO1")) {
            connection.clockOut(employeeIds[0]);
            input1.setDisable(false);
            pass1.setDisable(false);
            clockO1.setDisable(true);
            clockIn1.setDisable(false);
        } else if (clickedBtn.getId().equals("clockO2")) {
            connection.clockOut(employeeIds[1]);
            input2.setDisable(false);
            pass2.setDisable(false);
            clockO2.setDisable(true);
            clockIn2.setDisable(false);
        } else if (clickedBtn.getId().equals("clockO3")) {
            connection.clockOut(employeeIds[2]);
            input3.setDisable(false);
            pass3.setDisable(false);
            clockO3.setDisable(true);
            clockIn3.setDisable(false);
        } else if (clickedBtn.getId().equals("clockO4")) {
            connection.clockOut(employeeIds[3]);
            input4.setDisable(false);
            pass4.setDisable(false);
            clockO4.setDisable(true);
            clockIn4.setDisable(false);
        } else if (clickedBtn.getId().equals("clockO5")) {
            connection.clockOut(employeeIds[4]);
            input5.setDisable(false);
            pass5.setDisable(false);
            clockO5.setDisable(true);
            clockIn5.setDisable(false);
        } else if (clickedBtn.getId().equals("clockO6")) {
            connection.clockOut(employeeIds[5]);
            input6.setDisable(false);
            pass6.setDisable(false);
            clockO6.setDisable(true);
            clockIn6.setDisable(false);
        }
    }

    @FXML
    private void clockOutAll() throws IOException {
        for(int i = 0; i < 6; i++){
            if(employeeIds[i] != 0){
                connection.clockOut(employeeIds[i]);
            }

        }
        new Main().showLogInStage(new Stage());
        Stage stage = (Stage) bPane.getScene().getWindow();
        stage.close();
    }

}
