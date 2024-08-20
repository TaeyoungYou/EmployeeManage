package system.controller.locate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import system.management.location.Location;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocateDataController {
    private Location addedLocation;
    LocatePageController controller = LocateControllerManager.getLocatePageController();

    @FXML
    private Label dataText;


    public void putData(Location location){
        addedLocation = location;
        dataText.setText(location.getLocationName());
    }

    @FXML
    private void handleClicked(MouseEvent event) throws IOException {
        controller.showLocation(addedLocation);
    }

    @FXML private ImageView delBtn;
    @FXML
    private void delEnter(MouseEvent event) {
        delBtn.setImage(new Image(getClass().getResourceAsStream("/icon/del_blue.png")));
    }
    @FXML
    private void delExit(MouseEvent event) {
        delBtn.setImage(new Image(getClass().getResourceAsStream("/icon/del.png")));
    }

    @FXML
    private void delClicked(MouseEvent event) {
        controller.delLocation(addedLocation);
    }
}
