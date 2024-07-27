package system.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpDataController implements Initializable {


    private EmpPageController empPageController;

    @FXML
    private CheckBox check;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void putData(String name, String birth, String phone, String email, String exp, String role, String etc){
        this.name.setText(name);
        this.birth.setText(birth);
        this.phone.setText(phone);
        this.email.setText(email);
        this.exp.setText(exp);
        this.role.setText(role);
        this.etc.setText(etc);
    }

}
