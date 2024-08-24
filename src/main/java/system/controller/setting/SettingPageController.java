package system.controller.setting;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import system.management.path.ManageFile;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingPageController implements Initializable {
    private final String pathKey = "ManagePath";
    private final String tempKey = "ReportPath";

    @FXML private BorderPane mainPane;
    @FXML private TextField pathField;
    @FXML private TextField reportField;

    @FXML
    private void pathBtn(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if(selectedFile != null) {
            pathField.setText(selectedFile.getAbsolutePath());
        }else{
            pathField.setText("");
        }
        ManageFile.setPath(pathKey, pathField.getText());
    }
    @FXML
    private void reportBtn(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if(selectedFile != null) {
            reportField.setText(selectedFile.getAbsolutePath());
        }else{
            reportField.setText("");
        }
        ManageFile.setPath(tempKey, reportField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pathField.setText(ManageFile.getPath(pathKey));
        reportField.setText(ManageFile.getPath(tempKey));
    }
}
