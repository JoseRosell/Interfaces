module com.example.interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.interfaces to javafx.fxml;
    exports com.example.interfaces;
}