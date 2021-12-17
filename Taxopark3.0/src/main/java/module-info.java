module com.example.taxopark2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.taxopark2 to javafx.fxml;
    exports com.example.taxopark2;
}