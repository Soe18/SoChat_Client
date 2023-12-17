module com.example.demo7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    opens com.example.demo7 to javafx.fxml;
    exports com.example.demo7;
}