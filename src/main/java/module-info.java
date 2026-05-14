module com.passwordchkr {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.passwordchkr.controller to javafx.fxml;

    exports com.passwordchkr;
}