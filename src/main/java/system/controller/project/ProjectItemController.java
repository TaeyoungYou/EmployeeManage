package system.controller.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import system.management.project.Project;


public class ProjectItemController {
    @FXML private BorderPane itemPane;
    @FXML private Label itemText;

    private boolean clicked = false;
    private Project myProject;


    @FXML
    private void onClick(MouseEvent event) {
        ProjectPageController controller = ProjectControllerManager.getProjectPageController();
        controller.refreshProjectItems(myProject);
    }
    @FXML
    private void onEnter(MouseEvent event) {
        if(!clicked){
            setClickedItemColor();
        }
    }
    @FXML
    private void onExit(MouseEvent event) {
        if(!clicked){
            setUnclickedItemColor();
        }
    }


    public void setClickedItemColor(){
        itemPane.setStyle("-fx-background-color: #2B2D30");
        itemText.setStyle("-fx-text-fill: #388EF4");
    }
    public void setUnclickedItemColor(){
        itemPane.setStyle("-fx-background-color:   #26282B");
        itemText.setStyle("-fx-text-fill:  #818380");
    }
    public void setText(Project project) {
        itemText.setText(project.getProjectName());
        myProject = project;
    }
    public void setClicked() {
        this.clicked = true;
    }
}
