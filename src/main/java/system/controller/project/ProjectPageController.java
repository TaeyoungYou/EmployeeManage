package system.controller.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import system.controller.project.update_scene.ProjectDataController;
import system.management.project.Project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectPageController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private ScrollPane scrollPane;

    private static ObservableList<Project> projects = FXCollections.observableArrayList();
    private VBox projectsBox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectControllerManager.setProjectPageController(this);
        projectsBox.setSpacing(0);
        projectsBox.setFillWidth(true);
        refreshItems();
    }

    @FXML
    private ImageView addIcon;
    @FXML
    private void onClicked(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-add-page.fxml"));
        try{
            BorderPane pane = loader.load();
            mainPane.setCenter(pane);

        }catch (IOException e){}

    }
    @FXML
    private void onEntered(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/project_add_blue.png")));
    }
    @FXML
    private void onExited(MouseEvent event) {
        addIcon.setImage(new Image(getClass().getResourceAsStream("/icon/project_add.png")));
    }
    

    public void setProject(Project project) {
        projects.add(project);
        refreshItems();
    }
    public void refreshItems(){
        projectsBox.getChildren().clear();

        for (Project project : projects) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-item.fxml"));
            try {
                BorderPane pane = loader.load();
                ProjectItemController controller = loader.getController();
                controller.setText(project);
                projectsBox.getChildren().add(pane);
            } catch (IOException e) {
            }
        }

        scrollPane.setContent(projectsBox);
    }

    public void refreshProjectItems(Project proj){
        projectsBox.getChildren().clear();
        for(Project project : projects){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-item.fxml"));
            try{
                BorderPane pane = loader.load();
                ProjectItemController controller = loader.getController();
                controller.setText(project);
                if(project == proj){
                    controller.setClickedItemColor();
                    controller.setClicked();
                }
                projectsBox.getChildren().add(pane);
            }catch(IOException e){}
        }
        scrollPane.setContent(projectsBox);

        callProjectPane(proj);
    }

    public void callProjectPane(Project proj){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/system/app/project/project-data.fxml"));
        try{
            BorderPane pane = loader.load();
            ProjectDataController controller = loader.getController();
            controller.setAllData(proj);
            controller.passInstance(proj);

            mainPane.setCenter(pane);
        }catch(IOException e){}
    }

}
