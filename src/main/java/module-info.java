module system.employeemanagesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires javafx.web;
    requires com.sothawo.mapjfx;
    requires org.json;

    exports system.app;
    opens system.app to javafx.fxml;
    exports system.controller;
    opens system.controller to javafx.fxml;
    exports system.controller.employee;
    opens system.controller.employee to javafx.fxml;
    exports system.controller.setting;
    opens system.controller.setting to javafx.fxml;
    exports system.controller.locate;
    opens system.controller.locate to javafx.fxml;
    exports system.controller.project;
    opens system.controller.project to javafx.fxml;
    exports system.controller.project.add_scene;
    opens system.controller.project.add_scene to javafx.fxml;
    exports system.controller.project.update_scene;
    opens system.controller.project.update_scene to javafx.fxml;
}