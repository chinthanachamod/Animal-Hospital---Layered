package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {         // mulinma run wenn one fxml eka hdnw

    static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage1) throws Exception {
        stage = stage1;
        Parent rootNod = FXMLLoader.load(getClass().getResource("/View/LoginFormController.fxml"));
        Scene loginScene = new Scene(rootNod);
        stage.setScene(loginScene);
        stage.setTitle("Login To Animal Hospital");
        stage.show();

    }

    public static Stage getStagex() {
        return stage;
    }

}