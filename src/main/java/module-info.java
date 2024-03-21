module com.example.warehouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.warehouse to javafx.fxml;
    exports com.example.warehouse;
}