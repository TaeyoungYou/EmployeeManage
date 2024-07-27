package system.controller.employee;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;

public class EmpUpdateController {

    private EmpPageController empPageController;
    private int currentIndex;

    @FXML
    private TextField name;

    @FXML
    private TextField birth;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField exp;

    @FXML
    private TextField role;

    @FXML
    private TextField etc;


    @FXML
    private void submitButtonAction(MouseEvent event) {
        EmployeeList.updateEmployee(currentIndex, Employee.createEmployee(name.getText(), birth.getText(), phone.getText(), email.getText(), exp.getText(), role.getText(), etc.getText()));
        empPageController.refreshData();
        empPageController.dataField.setContent(empPageController.dataGrid);
    }

    public void setParentController(EmpPageController empPageController, int curIndex) {
        this.empPageController = empPageController;
        this.currentIndex = curIndex;
    }

}
