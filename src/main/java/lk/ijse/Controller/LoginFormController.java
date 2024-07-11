package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Launcher;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.VeterinaringBO;
import lk.ijse.bo.custom.impl.VeterinaringBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField UserText;
    public PasswordField UserPsswrd;
    public AnchorPane rootPane;
    VeterinaringBO veterinaringBO = (VeterinaringBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VETERINARIAN);

    public void SUSignInBtn(ActionEvent actionEvent) {
        //System.out.println("Hiiii");
        String username = UserText.getText();
        String password = UserPsswrd.getText();

        boolean isUserVerified = false;

        try {
            isUserVerified = veterinaringBO.checkCredintial(username, password);
            if (true) {//isUserVerified
                new Alert(Alert.AlertType.CONFIRMATION,"User is Verified");
                navigateToDashboard();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"UserName or Password is incorrect").show();
            throw new RuntimeException(e);
        }
    }

    private void navigateToDashboard() {
        try {
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/DashboardForm.fxml"));
            Scene scene = new Scene(rootNode);

            Stage stage = (Stage)this.rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setMaxWidth(1920);
            stage.setMaxHeight(1080);
            stage.centerOnScreen();
            stage.setTitle("Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SIsignUpBtn(ActionEvent actionEvent) throws IOException {

        Stage stage = Launcher.getStagex();

        Parent rootNod = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
        Scene loginScene = new Scene(rootNod);
        stage.setScene(loginScene);
        stage.setTitle("Sign Up To Animal Hospital");
        stage.show();
    }
}

// Dr.W.A.Chathuranga