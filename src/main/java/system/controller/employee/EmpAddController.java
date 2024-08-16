package system.controller.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;

import java.io.IOException;

public class EmpAddController {

    private VBox body = new VBox();

    @FXML
    private GridPane bodyGrid;

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
        EmployeeList.addEmployee(Employee.createEmployee(name.getText(), birth.getText(), phone.getText(), email.getText(), exp.getText(), role.getText(), etc.getText()));
        EmpPageController.employees.clear();
        EmpPageController.employees.addAll(EmployeeList.getEmployees());
    }

    public VBox getBody(){
        try{
            body.getChildren().clear();

            body.getChildren().add(bodyGrid);
            for(Employee emp: EmployeeList.getEmployees()){
                FXMLLoader dataLoader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-data-field.fxml"));
                GridPane pane = dataLoader.load();
                EmpDataController controller = dataLoader.getController();
                controller.putData(emp);
                body.getChildren().add(pane);
            }
        }catch(IOException e){}

        return body;
    }

}
