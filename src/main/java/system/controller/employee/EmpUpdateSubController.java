package system.controller.employee;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import system.management.employee.Employee;

public class EmpUpdateSubController {

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

    public TextField getName() {
        return name;
    }

    public TextField getBirth() {
        return birth;
    }

    public TextField getPhone() {
        return phone;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getExp() {
        return exp;
    }

    public TextField getRole() {
        return role;
    }

    public TextField getEtc() {
        return etc;
    }

    public String[] getData(){
        return new String[]{getName().getText(),getBirth().getText(),getPhone().getText(),getEmail().getText(),getExp().getText(),getRole().getText(),getEtc().getText()};
    }
}
