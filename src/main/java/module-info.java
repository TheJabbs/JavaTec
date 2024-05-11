module com.example.posjohonnyjavatecspring {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires jakarta.persistence;
    requires okhttp3;
    requires org.json;
    requires javafx.base;

    opens com.example.posjohonnyjavatecspring2023 to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example.posjohonnyjavatecspring2023.DTO to com.fasterxml.jackson.databind;
    exports com.example.posjohonnyjavatecspring2023.Entity to jakarta.persistence, com.fasterxml.jackson.databind;
    opens com.example.posjohonnyjavatecspring2023.DTO to javafx.base;
    exports com.example.posjohonnyjavatecspring2023;
}