package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.Launcher;

import java.io.IOException;

public class SignUpController {

    public void SUSignInBtn(ActionEvent actionEvent) throws IOException {

        Stage stage = Launcher.getStagex();

        Parent rootNod = FXMLLoader.load(getClass().getResource("/View/LoginFormController.fxml"));
        Scene loginScene = new Scene(rootNod);
        stage.setScene(loginScene);
        stage.setTitle("Sign Up To Animal Hospital");
        stage.show();
    }
}