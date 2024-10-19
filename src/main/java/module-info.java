module com.beanc.candicesta {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires java.sql;
    requires java.desktop;

    opens com.beanc.candicesta to javafx.fxml;
    exports com.beanc.candicesta;

    opens com.beanc.controller.initial to javafx.fxml;
    opens com.beanc.controller.workstation to javafx.fxml;
    opens com.beanc.controller.menu.file to javafx.fxml;
    opens com.beanc.controller.menu.edit to javafx.fxml;
    opens com.beanc.controller.menu.help to javafx.fxml;
    opens com.beanc.controller to javafx.fxml;
}