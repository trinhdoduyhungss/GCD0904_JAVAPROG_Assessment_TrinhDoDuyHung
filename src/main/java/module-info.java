module com.example.asm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.asm to javafx.fxml;
    exports com.example.asm;
}