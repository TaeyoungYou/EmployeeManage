package system.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.management.employee.EmpExel;
import system.management.employee.EmpFile;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        EmpFile.initProperties("src/main/resources/path/filePaths.csv");
        EmpExel.readEmpExel();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/system/app/basic-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        EmpFile.saveProperty("src/main/resources/path/filePaths.csv");
        EmpExel.saveEmpExel();

    }

    public static void main(String[] args) {
        launch();
    }
}
