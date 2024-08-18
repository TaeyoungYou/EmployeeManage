package system.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BasicPageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadContent("/system/app/home-page.fxml");
        homeLabel.setStyle("-fx-text-fill: #388EF4;");
        homeIcon.setImage(new Image(getClass().getResourceAsStream("/icon/home_blue.png")));
        selectedLabel = homeLabel;
    }

    @FXML private BorderPane mainContent;
    private void loadContent(String fxmlFile){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane content = loader.load();
            mainContent.setCenter(content);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private Label selectedLabel;
    @FXML
    private void handleClick(MouseEvent event){
        Label menuLabel = (Label) event.getSource();
        String fxmlFile = "";

        resetPoint();

        switch(menuLabel.getId()){
            case "homeLabel":
                fxmlFile = "/system/app/home-page.fxml";
                homeLabel.setStyle("-fx-text-fill: #388EF4;");
                homeIcon.setImage(new Image(getClass().getResourceAsStream("/icon/home_blue.png")));
                break;
            case "reportLabel":
                fxmlFile = "/system/app/report-page.fxml";
                reportLabel.setStyle("-fx-text-fill: #388EF4;");
                reportIcon.setImage(new Image(getClass().getResourceAsStream("/icon/report_blue.png")));
                break;
            case "recordLabel":
                fxmlFile = "/system/app/record-page.fxml";
                recordLabel.setStyle("-fx-text-fill: #388EF4;");
                recordIcon.setImage(new Image(getClass().getResourceAsStream("/icon/record_blue.png")));
                break;
            case "empLabel":
                fxmlFile = "/system/app/employee/emp-page.fxml";
                empLabel.setStyle("-fx-text-fill: #388EF4;");
                empIcon.setImage(new Image(getClass().getResourceAsStream("/icon/employee_blue.png")));
                break;
            case "settingLabel":
                fxmlFile = "/system/app/setting-page.fxml";
                settingLabel.setStyle("-fx-text-fill: #388EF4;");
                settingIcon.setImage(new Image(getClass().getResourceAsStream("/icon/setting_blue.png")));

        }

        selectedLabel = menuLabel;
        loadContent(fxmlFile);
    }

    private void resetPoint(){
        homeLabel.setStyle("-fx-text-fill: #818380;");
        reportLabel.setStyle("-fx-text-fill: #818380;");
        recordLabel.setStyle("-fx-text-fill: #818380;");
        empLabel.setStyle("-fx-text-fill: #818380;");
        settingLabel.setStyle("-fx-text-fill: #818380;");

        homeIcon.setImage(new Image(getClass().getResourceAsStream("/icon/home.png")));
        reportIcon.setImage(new Image(getClass().getResourceAsStream("/icon/report.png")));
        recordIcon.setImage(new Image(getClass().getResourceAsStream("/icon/record.png")));
        empIcon.setImage(new Image(getClass().getResourceAsStream("/icon/employee.png")));
        settingIcon.setImage(new Image(getClass().getResourceAsStream("/icon/setting.png")));
    }


    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    @FXML private ImageView exitButton;
    @FXML
    private void MouseClickedExit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("종료");
        alert.setHeaderText(null);
        alert.setContentText("종료 하시겠습니까?");

        Stage stage = (Stage) exitButton.getScene().getWindow();

        alert.setX(stage.getX() + stage.getWidth()/2 - alert.getDialogPane().getWidth()/2);
        alert.setY(stage.getY() + stage.getHeight()/2 - alert.getDialogPane().getHeight()/2);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get()==ButtonType.OK){
            stage.close();
        }
    }
    @FXML
    private void handleMouseEnterPower(MouseEvent event) {
        exitButton.setImage(new Image(getClass().getResourceAsStream("/icon/power_blue.png")));
    }
    @FXML
    private void handleMouseExitPower(MouseEvent event) {
        exitButton.setImage(new Image(getClass().getResourceAsStream("/icon/power.png")));
    }

    @FXML private Label homeLabel;
    @FXML private ImageView homeIcon;
    @FXML
    private void handleMouseEnterHome(MouseEvent event) {
        if(selectedLabel != homeLabel){
            homeLabel.setStyle("-fx-text-fill: #388EF4;");
            homeIcon.setImage(new Image(getClass().getResourceAsStream("/icon/home_blue.png")));
        }
    }
    @FXML
    private void handleMouseExitHome(MouseEvent event) {
        if(selectedLabel != homeLabel){
            homeLabel.setStyle("-fx-text-fill: #818380;");
            homeIcon.setImage(new Image(getClass().getResourceAsStream("/icon/home.png")));
        }
    }

    @FXML private Label reportLabel;
    @FXML private ImageView reportIcon;
    @FXML
    private void handleMouseEnterReport(MouseEvent event) {
        if(selectedLabel != reportLabel){
            reportLabel.setStyle("-fx-text-fill: #388EF4;");
            reportIcon.setImage(new Image(getClass().getResourceAsStream("/icon/report_blue.png")));
        }
    }
    @FXML
    private void handleMouseExitReport(MouseEvent event) {
        if(selectedLabel != reportLabel){
            reportLabel.setStyle("-fx-text-fill: #818380;");
            reportIcon.setImage(new Image(getClass().getResourceAsStream("/icon/report.png")));
        }
    }

    @FXML private Label recordLabel;
    @FXML private ImageView recordIcon;
    @FXML
    private void handleMouseEnterRecord(MouseEvent event) {
        if(selectedLabel != recordLabel){
            recordLabel.setStyle("-fx-text-fill: #388EF4;");
            recordIcon.setImage(new Image(getClass().getResourceAsStream("/icon/record_blue.png")));
        }
    }
    @FXML
    private void handleMouseExitRecord(MouseEvent event) {
        if(selectedLabel != recordLabel) {
            recordLabel.setStyle("-fx-text-fill: #818380;");
            recordIcon.setImage(new Image(getClass().getResourceAsStream("/icon/record.png")));
        }
    }

    @FXML private Label empLabel;
    @FXML private ImageView empIcon;
    @FXML
    private void handleMouseEnterEmp(MouseEvent event) {
        if(selectedLabel != empLabel) {
            empLabel.setStyle("-fx-text-fill: #388EF4;");
            empIcon.setImage(new Image(getClass().getResourceAsStream("/icon/employee_blue.png")));
        }
    }
    @FXML
    private void handleMouseExitEmp(MouseEvent event) {
        if(selectedLabel != empLabel) {
            empLabel.setStyle("-fx-text-fill: #818380;");
            empIcon.setImage(new Image(getClass().getResourceAsStream("/icon/employee.png")));
        }
    }

    @FXML private Label settingLabel;
    @FXML private ImageView settingIcon;
    @FXML
    private void handleMouseEnterSetting(MouseEvent event) {
        if(selectedLabel != settingLabel){
            settingLabel.setStyle("-fx-text-fill: #388EF4;");
            settingIcon.setImage(new Image(getClass().getResourceAsStream("/icon/setting_blue.png")));
        }
    }
    @FXML
    private void handleMouseExitSetting(MouseEvent event) {
        if(selectedLabel != settingLabel) {
            settingLabel.setStyle("-fx-text-fill: #818380;");
            settingIcon.setImage(new Image(getClass().getResourceAsStream("/icon/setting.png")));
        }
    }


}

