package system.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpUpdateController {

    private List<Integer> toUpdate = new ArrayList<>();
    private VBox body = new VBox();

    @FXML private ScrollPane dataField;

    @FXML private ImageView updateIcon;
    @FXML
    private void updateEnter(MouseEvent event) {
        updateIcon.setImage(new Image(getClass().getResourceAsStream("/icon/update_check_blue.png")));
    }
    @FXML
    private void updateExit(MouseEvent event) {
        updateIcon.setImage(new Image(getClass().getResourceAsStream("/icon/update_check.png")));
    }


    @FXML
    private void updateClick(MouseEvent event) {
        for(Integer i : toUpdate) {
            GridPane pane = (GridPane) body.getChildren().get(i);
            EmpUpdateSubController controller = (EmpUpdateSubController) pane.getUserData();

            EmployeeList.updateEmployee(EmployeeList.getEmployees().get(i), Employee.createEmployee(controller.getData()));
        }


        EmpPageController.employees.clear();
        EmpPageController.employees.addAll(EmployeeList.getEmployees());
    }

    public void setList(List<Integer> employees){
        toUpdate.addAll(employees);

        body.getChildren().clear();

        for(int i=0;i<EmployeeList.getEmployees().size();i++){
            if(toUpdate.contains(i)){
                try{
                    FXMLLoader dataLoader = new FXMLLoader(getClass().getResource("/system/app/employee/update-sub-field.fxml"));
                    GridPane pane = dataLoader.load();
                    EmpUpdateSubController controller = dataLoader.getController();
                    pane.setUserData(controller);
                    controller.putData(EmployeeList.getEmployees().get(i));
                    body.getChildren().add(pane);
                }catch(IOException e){}
            }else{

                try{
                    FXMLLoader dataLoader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-data-field.fxml"));
                    GridPane pane = dataLoader.load();
                    EmpDataController controller = dataLoader.getController();
                    pane.setUserData(controller);
                    controller.putData(EmployeeList.getEmployees().get(i));
                    body.getChildren().add(pane);
                }catch(IOException e){}
            }
        }
        dataField.setContent(body);
    }

}
