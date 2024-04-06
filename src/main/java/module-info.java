module com.promeme {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens com.promeme to javafx.fxml;
    exports com.promeme;
    exports com.promeme.view;
    opens com.promeme.view to javafx.fxml;
    exports com.promeme.controller;
    opens com.promeme.controller to javafx.fxml;

}