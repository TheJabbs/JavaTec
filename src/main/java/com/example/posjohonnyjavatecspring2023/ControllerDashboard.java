package com.example.posjohonnyjavatecspring2023;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerDashboard {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label greet, timeL;
    @FXML
    private Button clockOut;
    @FXML
    private Button adminbtn;

    DatabaseConnection connection = Main.getDefaultToken();
    public void initialize() {
        setGreet();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        System.out.println("\n\n" + connection.getEmployee().getEmployeeStatus() + "\n\n");
        if(TempStorage.employee.getEmployeeStatus().charAt(0) != 'a'){
            adminbtn.setDisable(true);
        }
    }
    public void setGreet(){
        System.out.println("Setting greet");
        greet.setText("Welcome, " + TempStorage.employee.getEmployeeFname() + " " + TempStorage.employee.getEmployeeLname() + "!  ");
    }
    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(new Date());

        timeL.setText(currentTime);
    }

    @FXML
    protected void clickOutClicked() throws IOException {
        new ApiClient().clockOut();
        System.out.println("Clocking out");
        Stage stage = (Stage) clockOut.getScene().getWindow();
        stage.close();
        new Main().showLogInStage(new Stage());
    }

    @FXML
    protected void onCashierbtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cashier-view.fxml"));
            borderPane.setCenter(loader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onKitchenbtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Kitchen-view.fxml"));
            borderPane.setCenter(loader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAdminbtnClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin-view.fxml"));
            borderPane.setCenter(loader.load());
            System.out.println("Admin clicked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
