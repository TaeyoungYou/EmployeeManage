package system.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmpPageController implements Initializable {

    private ObservableList<Employee> employees;
    public VBox dataGrid;

    @FXML public ScrollPane dataField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees = FXCollections.observableArrayList(EmployeeList.getEmployees());
        dataGrid = new VBox();
        refreshData();
        dataField.setContent(dataGrid);
    }

    public void refreshData() {
        dataGrid.getChildren().clear();
        dataField.setContent(dataGrid);

        employees.clear();
        employees.addAll(EmployeeList.getEmployees());

        for(Employee employee : employees) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-data-field.fxml"));
                GridPane grid = loader.load();
                EmpDataController controller = loader.getController();
                controller.putData(employee.getName(),employee.getBirth(),employee.getPhone(),employee.getEmail(),employee.getExprience(),employee.getRole(),employee.getEtc());
                dataGrid.getChildren().add(grid);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void addButtonClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-add-field.fxml"));
            GridPane pane = loader.load();
            EmpAddController controller = loader.getController();
            controller.setParentController(this);
            refreshData();
            dataGrid.getChildren().addFirst(pane);
            dataField.setContent(dataGrid);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void updateClick(MouseEvent event){
        for(Node node: dataGrid.getChildren()){
            GridPane pane = (GridPane) node;
            Node firstChild = pane.getChildren().get(0);
            if(firstChild instanceof CheckBox){
                CheckBox checkBox = (CheckBox) firstChild;
                if(checkBox.isSelected()){
                    try{
                        FXMLLoader addFile = new FXMLLoader(getClass().getResource("/system/app/employee/emp-update-field.fxml"));
                        GridPane addGrid = addFile.load();

                        for(int i = 1; i< pane.getChildren().size(); i++){
                            ((TextField) addGrid.getChildren().get(i)).setText(((TextField) pane.getChildren().get(i)).getText());
                        }
                        int curIndex = dataGrid.getChildren().indexOf(node);
                        dataGrid.getChildren().set(curIndex,addGrid);
                        EmpUpdateController controller = addFile.getController();
                        controller.setParentController(this,curIndex);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @FXML
    private void delClick(MouseEvent event){
        for(Node node: dataGrid.getChildren()){
            GridPane pane = (GridPane) node;
            Node firstChild = pane.getChildren().get(0);
            if(firstChild instanceof CheckBox){
                CheckBox checkBox = (CheckBox) firstChild;
                if(checkBox.isSelected()){
                    EmployeeList.removeEmployee(employees.get(dataGrid.getChildren().indexOf(node)));
                }
            }
        }
        refreshData();
    }

    @FXML private ImageView addIcon;
    @FXML
    private void addMouseEnter(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/add_red.png")));
    }
    @FXML
    private void addMouseExit(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/add.png")));
    }

    @FXML private ImageView updateIcon;
    @FXML
    private void updateEnter(MouseEvent event) {
        updateIcon.setImage(new Image(getClass().getResourceAsStream("/icon/update_yellow.png")));
    }
    @FXML
    private void updateExit(MouseEvent event) {
        updateIcon.setImage(new Image(getClass().getResourceAsStream("/icon/update.png")));
    }

    @FXML private ImageView delIcon;
    @FXML
    private void delEnter(MouseEvent event) {
        delIcon.setImage(new Image(getClass().getResourceAsStream("/icon/del_green.png")));
    }
    @FXML
    private void delExit(MouseEvent event) {
        delIcon.setImage(new Image(getClass().getResourceAsStream("/icon/del.png")));
    }
}
