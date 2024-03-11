module com.example.posjohonnyjavatecspring2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.fasterxml.jackson.databind;

    opens com.example.posjohonnyjavatecspring2023 to javafx.fxml, com.fasterxml.jackson.databind;

    exports com.example.posjohonnyjavatecspring2023;
}