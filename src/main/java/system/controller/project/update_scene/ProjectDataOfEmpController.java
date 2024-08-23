package system.controller.project.update_scene;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import system.controller.project.ProjectControllerManager;
import system.controller.project.add_scene.ProjectAddController;
import system.management.employee.Employee;

public class ProjectDataOfEmpController {

    @FXML private Label empText;
    @FXML private BorderPane itemPane;

    private boolean clicked = false;
    private Employee employee;
    private ProjectDataController controller = ProjectControllerManager.getProjectDataController();

    @FXML
    private void onClicked(MouseEvent event) {
        if(clicked){
            clicked = false;
            empText.setStyle("-fx-text-fill:  #818380");

            controller.delEmployeeToAdd(employee);
        }else{
            clicked = true;
            empText.setStyle("-fx-text-fill: #388EF4");

            controller.setEmployeeToAdd(employee);
        }
    }
    @FXML
    private void onMouseEnter(MouseEvent event) {
        if(!clicked){
            empText.setStyle("-fx-text-fill: #388EF4");
        }
    }
    @FXML
    private void onMouseExit(MouseEvent event) {
        if(!clicked){
            empText.setStyle("-fx-text-fill:  #818380");
        }
    }


    public void setText(Employee employee) {
        this.empText.setText(employee.getName());
        this.employee = employee;
    }
}
