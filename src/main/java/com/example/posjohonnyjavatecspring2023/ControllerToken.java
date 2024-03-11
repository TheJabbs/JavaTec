package com.example.posjohonnyjavatecspring2023;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class ControllerToken {
    @FXML
    private Label errorLabelToken;

    @FXML
    private TextField tokenInput;

    @FXML
    private Button exitBtn;

    private FileWriter myWriter;

    DatabaseConnection connection = Main.getDefaultToken();

    @FXML
    protected void onKeyPressed(javafx.scene.input.KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")) {
            activateClicked();
        }
    }

    @FXML
    protected void activateClicked() throws IOException {
        try {
            myWriter = new FileWriter("Token.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        System.out.println(tokenInput.getText());

        if (connection.validateToken(tokenInput.getText())) {
            errorLabelToken.setText("This code is either expired or invalid.");
        } else {
            errorLabelToken.setText("Success!");
            try{
                myWriter.flush();
                myWriter.write(tokenInput.getText());
                myWriter.close();
            }catch (Exception e){
                System.out.println("Error; " + e.toString());
            }

            Stage stage = (Stage) tokenInput.getScene().getWindow();
            stage.close();

            Main main = new Main();
            main.showLogInStage(new Stage());

        }
    }

    @FXML
    protected void exitClicked() {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }
}