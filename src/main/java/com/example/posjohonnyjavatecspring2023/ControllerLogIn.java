package com.example.posjohonnyjavatecspring2023;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerLogIn {
    @FXML
    private Label closeBtn;
    @FXML
    private TextField usernameI;
    @FXML
    private PasswordField passwordI;
    @FXML
    private Label errorL;
    DatabaseConnection connection = Main.getDefaultToken();

    @FXML
    protected void closeBtnClicked(){
        closeBtn.requestFocus();
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLogInBtnClicked() throws IOException {
        System.out.println(connection.toString());
        errorL.setText("");
        if(usernameI.getText().isBlank() || passwordI.getText().isBlank()) {
            errorL.setText("Please enter your username and password.");
        } else{
//            if(connection.validateEmployee(usernameI.getText(), passwordI.getText())){
//                System.out.println("Success!");
//                closeBtnClicked();
//                Stage dashboardStage = new Stage();
//                new Main().showDashboardStage(dashboardStage);
//                dashboardStage.setOnHidden(e -> {
//                            System.out.println("Closing the dashboard");
//                            connection.clockOut();
//                        });
//                connection.clockIn();
//            }else {
//                errorL.setText("Invalid username or password.");
//            }
            ApiClient apiClient = new ApiClient();
            try {
                if(apiClient.auth(usernameI.getText(), passwordI.getText())){
                    System.out.println("Success!");
                    closeBtnClicked();
                    Stage dashboardStage = new Stage();
                    new Main().showDashboardStage(dashboardStage);
                    dashboardStage.setOnHidden(e -> {
                        System.out.println("Closing the dashboard");
                        connection.clockOut();
                    });
                    apiClient.clockIn();
                }else {
                    errorL.setText("Invalid username or password.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void onKeyPressed1(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            passwordI.requestFocus();
        }
    }
    @FXML
    protected void onKeyPressed2(javafx.scene.input.KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER"))
            onLogInBtnClicked();
    }
    @FXML
    private void setCloseBtnHover() {
        closeBtn.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
    }
    @FXML
    private void setCloseBtnIdle() {
        closeBtn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ff0000");
    }

}
