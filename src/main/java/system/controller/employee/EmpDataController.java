package system.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import system.management.employee.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpDataController {

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

    public void putData(Employee employee){
        name.setText(employee.getName());
        birth.setText(employee.getBirth());
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail());
        exp.setText(employee.getExprience());
        role.setText(employee.getRole());
        etc.setText(employee.getEtc());
    }

}
