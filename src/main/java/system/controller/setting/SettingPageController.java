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

    @FXML private BorderPane mainPane;
    @FXML private TextField pathField;

    @FXML
    private void pathBtn(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if(selectedFile != null) {
            pathField.setText(selectedFile.getAbsolutePath());
        }else{
            pathField.setText("");
        }
        ManageFile.setPath(pathField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pathField.setText(ManageFile.getPath());
    }
}
