module com.finalalp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.finalalp to javafx.fxml;
    exports com.finalalp;
}
