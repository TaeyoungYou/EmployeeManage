module system.employeemanagesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports system.app;
    opens system.app to javafx.fxml;
    exports system.controller;
    opens system.controller to javafx.fxml;
    exports system.controller.employee;
    opens system.controller.employee to javafx.fxml;
}