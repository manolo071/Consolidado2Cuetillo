module advance.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens advance.demo to javafx.fxml;
    exports advance.demo;
}
