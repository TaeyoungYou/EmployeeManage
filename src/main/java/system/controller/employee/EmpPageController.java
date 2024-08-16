package system.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import system.management.employee.Employee;
import system.management.employee.EmployeeList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpPageController implements Initializable {

    public static ObservableList<Employee> employees = FXCollections.observableArrayList();
    private VBox dataGrid = new VBox();

    @FXML private ScrollPane dataField;
    @FXML private VBox mainVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees.addListener((ListChangeListener<Employee>) changes -> {
            while(changes.next()) {
                if(changes.wasAdded() || changes.wasRemoved()){
                    refreshData();
                }

            }
        });
        employees.clear();
        employees.addAll(EmployeeList.getEmployees());
    }

    public void refreshData() {
        dataGrid.getChildren().clear();

        for(Employee employee : employees) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-data-field.fxml"));
                GridPane grid = loader.load();
                EmpDataController controller = loader.getController();
                controller.putData(employee);
                dataGrid.getChildren().add(grid);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(addIcon.isVisible() == false){
            addIcon.setVisible(true);
            updateIcon.setVisible(true);
            delIcon.setVisible(true);
            mainPane.setCenter(mainVBox);
        }

        dataField.setContent(dataGrid);
    }


    @FXML
    private void addButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-add-field.fxml"));
        loader.load();
        EmpAddController controller = loader.getController();
        dataGrid = controller.getBody();
        dataField.setContent(dataGrid);
    }

    @FXML private BorderPane mainPane;
    @FXML
    private void updateClick(MouseEvent event){
        List<Integer> indexList = new ArrayList<>();
        int i = 0;
        for(Node pane: dataGrid.getChildren()){
            CheckBox checkBox = (CheckBox) (((GridPane)pane).getChildren().getFirst());
            if(checkBox.isSelected()){
                indexList.add(i);
            }
            i++;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/employee/emp-update-field.fxml"));
            VBox pane = loader.load();
            EmpUpdateController controller = loader.getController();
            controller.setList(indexList);
            mainPane.setCenter(pane);

            addIcon.setVisible(false);
            updateIcon.setVisible(false);
            delIcon.setVisible(false);

        }catch(IOException e){}
    }

    @FXML
    private void delClick(MouseEvent event){
        int i = 0;
        for(Node pane: dataGrid.getChildren()){
            CheckBox checkbox = (CheckBox)((GridPane)pane).getChildren().getFirst();
            if(checkbox.isSelected()){
                EmployeeList.removeEmployee(employees.get(i));
            }
            i++;
        }
        employees.clear();
        employees.addAll(EmployeeList.getEmployees());
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

    @FXML private Label saveLabel;
    @FXML
    private void saveMouseEnter(MouseEvent event){
        saveLabel.setStyle("-fx-text-fill: #388EF4;");
    }
    @FXML
    private void saveMouseExit(MouseEvent event){
        saveLabel.setStyle("-fx-text-fill: #EEEEEE;");
    }

    @FXML private Label loadLabel;
    @FXML
    private void loadMouseEnter(MouseEvent event){
        loadLabel.setStyle("-fx-text-fill: #388EF4;");
    }
    @FXML
    private void loadMouseExit(MouseEvent event){
        loadLabel.setStyle("-fx-text-fill: #EEEEEE;");
    }

    @FXML
    private void saveClicked(MouseEvent mouseEvent) {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("직원 저장");

    }

    @FXML
    private void loadClicked(MouseEvent event) {

    }
}
