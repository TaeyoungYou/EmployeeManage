package system.controller.project.add_scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import system.controller.project.ProjectControllerManager;
import system.controller.project.ProjectEmpController;
import system.controller.project.ProjectPageController;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;
import system.management.location.Location;
import system.management.location.LocationList;
import system.management.project.Project;
import system.management.project.ProjectList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectAddController implements Initializable {
    @FXML private ImageView addIcon;
    @FXML private TextField projectNameField;
    @FXML private ChoiceBox<String> placeChoice;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ScrollPane addScroll;
    @FXML private ScrollPane totalScroll;

    private VBox totalBox = new VBox();
    private VBox addBox = new VBox();
    private List<Employee> employeeList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectControllerManager.setProjectAddController(this);
        setChoiceBox();
        initTotalScroll();
    }
    @FXML
    private void handleMouseEnterAdd(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/add_right_blue.png")));
    }
    @FXML
    private void handleMouseExitAdd(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/add_right.png")));
    }
    @FXML
    private void onClicked(MouseEvent event) {
        addBox.getChildren().clear();
        for(Employee employee : employeeList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-emp.fxml"));
            try{
                BorderPane pane = loader.load();
                ProjectEmpController controller = loader.getController();
                controller.setText(employee);
                addBox.getChildren().add(pane);
            }catch (IOException e){}

            addScroll.setContent(addBox);
        }
    }
    @FXML
    private void submitClicked(ActionEvent event) {
        if(projectNameField.getText().isBlank()){
            return;
        }
        ProjectPageController controller = ProjectControllerManager.getProjectPageController();
        Project newProject = new Project(projectNameField.getText(),startDate.getValue(),endDate.getValue(),getLocation(placeChoice.getValue()),employeeList);
        controller.setProject(newProject);
        ProjectList.add(newProject);
        resetField();
    }


    private Location getLocation(String locationName){
        for(Location location: LocationList.getLocations()){
            if(location.getLocationName().equals(locationName)){
                return location;
            }
        }
        return null;
    }
    private void resetField(){
        projectNameField.clear();
        setChoiceBox();
        startDate.setValue(null);
        endDate.setValue(null);
        employeeList.clear();
        onClicked(null);
        initTotalScroll();
    }
    private void setChoiceBox(){
        placeChoice.getItems().clear();
        List<String> locationNames = LocationList.getLocations().stream().map(Location::getLocationName).toList();
        placeChoice.getItems().addAll(locationNames);
    }
    public void setEmployeeToAdd(Employee employee){
        employeeList.add(employee);
    }
    public void delEmployeeToAdd(Employee employee){
        employeeList.remove(employee);
    }
    private void initTotalScroll(){
        totalBox.getChildren().clear();
        for(Employee employee: EmployeeList.getEmployees()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-emp-data.fxml"));
            try{
                BorderPane pane = loader.load();
                ProjectEmpDataController controller = loader.getController();
                controller.setText(employee);
                totalBox.getChildren().add(pane);
            }catch (IOException e){}

            totalScroll.setContent(totalBox);
        }
    }
}
