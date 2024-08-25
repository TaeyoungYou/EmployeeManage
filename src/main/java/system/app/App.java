package system.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.management.employee.EmpExel;
import system.management.location.LocationExel;
import system.management.path.ManageFile;
import system.management.project.ProjectExel;
import system.management.report.ReportProject;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ManageFile.initProperties("src/main/resources/path/filePaths.csv");
        EmpExel.readEmpExel();
        LocationExel.readLocationExel();
        ProjectExel.readProjectExel();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/system/app/basic-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        ManageFile.saveProperty("src/main/resources/path/filePaths.csv");
        EmpExel.saveEmpExel();
        LocationExel.saveLocationExel();
        ProjectExel.saveProjectExel();
        ReportProject.exportReport();
    }

    public static void main(String[] args) {
        launch();
    }
}
