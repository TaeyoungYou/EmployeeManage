module system.employeemanagesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    exports system.app;
    opens system.app to javafx.fxml;
    exports system.controller;
    opens system.controller to javafx.fxml;
    exports system.controller.employee;
    opens system.controller.employee to javafx.fxml;
    exports system.controller.setting;
    opens system.controller.setting to javafx.fxml;
}