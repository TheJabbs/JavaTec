package com.example.posjohonnyjavatecspring2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void start(Stage stage) throws IOException {
        try {
            File tokenFile = new File("token.txt");

            if (!tokenFile.exists()) {
                tokenFile.createNewFile();
                System.out.println("Entering the first time setup stage");
                showFirstTimeSetupStage(stage);
            } else {
                System.out.println("The file exists " + tokenFile.toString());

                Scanner read = new Scanner(tokenFile);
                String token = tokenFile.length() == 0 ? "" : read.nextLine();
                System.out.println("The token is: " + token);
                read.close();

                System.out.println("The file exists and the token is: " + token);

                if (databaseConnection.validateToken(token)) {
                    System.out.println("Entering the token setup stage");
                    showFirstTimeSetupStage(stage);
                } else {
                    System.out.println("Enter dashboard");
                    showLogInStage(stage);

                }
            }
        } catch (IOException e) {
            System.out.println("An error has occurred: " + e.toString());
        }

    }

    public void showLogInStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 677, 407);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Log In Application");
        stage.setScene(scene);
        stage.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader1.load(), 677, 407);
        Stage stage2 = new Stage();
        stage2.setResizable(false);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setTitle("Log In Application");
        stage2.setScene(scene2);
        stage2.show();
    }

    private void showFirstTimeSetupStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Token-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),618,406);
        stage.setTitle("Token Authentification");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void showDashboardStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1290,777);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static DatabaseConnection getDefaultToken() {
        return databaseConnection;
    }

}