package system.controller.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import system.management.employee.Employee;

public class ProjectEmpController {

    @FXML private Label empText;

    public void setText(Employee employee) {
        this.empText.setText(employee.getName());
    }

}
