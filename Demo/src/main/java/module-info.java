module advance.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens advance.demo to javafx.fxml;
    opens advance.demo.clss to javafx.base;
    exports advance.demo;
}
    
