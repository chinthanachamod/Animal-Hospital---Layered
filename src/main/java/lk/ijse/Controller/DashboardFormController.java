package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private JFXButton btnMedDetailes;

    public AnchorPane rootPane;
    public AnchorPane ControlArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControlArea.getChildren().removeAll();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Home.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);


        //
        LocalDateTime time = LocalDateTime.now();
        int hour = time.getHour();

        String text;

        if(hour >= 6 && hour < 12){
            text = "Good morning";
        }  else if(hour >= 12 && hour < 18){
            text = "Good afternoon";
        } else {
            text = "Good evening";
        }

        //

    }

    @FXML
    void btnMedDetailesOnActionClick(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/TransectionPresc.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }
    public void onPetActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Pet.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onHomeActionClick(ActionEvent actionEvent) throws IOException {
        ControlArea.getChildren().removeAll();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Home.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onPetOwnerActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/PetOwner.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onAppointmentActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Appointment.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onPrescriptionActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Prescription.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onMedicineActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Medicine.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }

    public void onSupplierActionClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Supplier.fxml"));
        AnchorPane anchorPane = loader.load();
        ControlArea.getChildren().removeAll();
        ControlArea.getChildren().setAll(anchorPane);
    }


    private void createStageScene(String path) {
        try {
            Parent rootNode = FXMLLoader.load(this.getClass().getResource(path));
            Scene scene = new Scene(rootNode);

            Stage stage = (Stage)this.rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}