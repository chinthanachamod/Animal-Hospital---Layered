package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AppoinmentBO;
import lk.ijse.bo.custom.PetOwnerBO;
import lk.ijse.bo.custom.VeterinaringBO;
import lk.ijse.bo.custom.impl.AppoinmentBOImpl;
import lk.ijse.bo.custom.impl.PetOwnerBOImpl;
import lk.ijse.bo.custom.impl.VeterinaringBOImpl;
import lk.ijse.dto.AppointmentDTO;
import lk.ijse.dto.PetOwnerDTO;
import lk.ijse.dto.VeterinarianDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextField;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentController {



    @FXML
    private DatePicker DatePicker;

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbPetOwner;

    @FXML
    private ComboBox<String> cmbVeternarian;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private TableColumn<?, ?> colAppointmentID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPetOwnerID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colVeternarianID;

    @FXML
    private TableView<AppointmentDTO> tblAppointment;

    @FXML
    private JFXTextField txtAppointment;
    AppoinmentBO appoinmentBO = (AppoinmentBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.APPOINMENT);
    PetOwnerBO petOwnerBO = (PetOwnerBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PET_OWNER);
    VeterinaringBO veterinaringBO = (VeterinaringBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VETERINARIAN);

    public void initialize() {
        setCellValueFactory();
        getPetOwnerIds();
        getVeternarianIds();
        loadAllBooking();

    }

    private void setCellValueFactory() {
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPetOwnerID.setCellValueFactory(new PropertyValueFactory<>("petOwId"));
        colVeternarianID.setCellValueFactory(new PropertyValueFactory<>("vetId"));
    }

    private void loadAllBooking() {
        ObservableList<AppointmentDTO> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentDTO> AppointmentList = appoinmentBO.getAllAppoinment();
            obList.addAll(AppointmentList);
            tblAppointment.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error loading appointments: " + e.getMessage());
        }
    }

    @FXML
    void DatePickerOnAction(ActionEvent event) {
        LocalDate selectedDate = DatePicker.getValue();
        if (selectedDate != null) {
            System.out.println("Selected date: " + selectedDate);
        } else {
            System.out.println("No date selected");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String appId = txtAppointment.getText();
        String time = String.valueOf(LocalTime.now());
        String petOwId = cmbPetOwner.getValue();
        String vetId = cmbVeternarian.getValue();

        System.out.println("//////////////////////////// "+time);

        LocalDate selectedDate = DatePicker.getValue();
        if (selectedDate == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a booking date.");
            return;
        }

        Date appointmentDate = Date.valueOf(selectedDate);

        AppointmentDTO appointment = new AppointmentDTO(appId, appointmentDate, time, petOwId, vetId);

        try {
            boolean isAppointmentSaved = appoinmentBO.saveAppoinment(appointment);
            if (isAppointmentSaved) {
                showAlert(Alert.AlertType.CONFIRMATION, "Booking placed successfully!");
                loadAllBooking();
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to save Booking!");
            }
        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Error saving appointment: " + ex.getMessage());
        }
    }


    @FXML
    void cmbPetOwnerOnAction(ActionEvent event) {
        String id = cmbPetOwner.getValue();
        try {
            PetOwnerDTO petOwner = petOwnerBO.petOwnersearchById(id);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while searching for showroom: " + e.getMessage());
        }
    }

    @FXML
    void cmbVeternarianOnAction(ActionEvent event) {
        String id = cmbVeternarian.getValue();
        try {
            VeterinarianDTO veterinarian = veterinaringBO.veterinaringSearchById(id);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while searching for showroom: " + e.getMessage());
        }
    }

    private void getPetOwnerIds() {
        try {
            List<String> idList = petOwnerBO.getPetOwnerValue();
            cmbPetOwner.setItems(FXCollections.observableArrayList(idList));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error fetching Pet Owner IDs: " + e.getMessage());
        }
    }

    private void getVeternarianIds() {
        try {
            List<String> idList = veterinaringBO.getVeterinaringValue();
            cmbVeternarian.setItems(FXCollections.observableArrayList(idList));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error fetching Veterinarian IDs: " + e.getMessage());
        }
    }

    @FXML
    void onAppointmentBackClick(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
        Stage stage = (Stage) MainAnchorpane.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btnUpdateOnAction(ActionEvent event) {
        AppointmentDTO selectedAppointment = tblAppointment.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a booking to update.");
            return;
        }
        txtAppointment.setText(selectedAppointment.getAppId());
        String time = txtTime.getText();
        txtTime.setText(selectedAppointment.getTime());
        selectedAppointment.setTime(time);

        try {
            boolean isUpdated = appoinmentBO.updateAppoinment(selectedAppointment);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Booking updated successfully!").show();
                loadAllBooking();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update booking!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating booking: " + e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtAppointment.clear();
        txtTime.clear();
        cmbPetOwner.getSelectionModel().clearSelection();
        cmbVeternarian.getSelectionModel().clearSelection();
        DatePicker.setValue(null);
    }

    public void btnDeleteOnAction(ActionEvent event) {
        AppointmentDTO selectedAppointment = tblAppointment.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select booking to Appointment!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Appointment?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                boolean isDeleted = appoinmentBO.deleteAppoinment(selectedAppointment.getAppId());
                if (isDeleted) {
                    tblAppointment.getItems().remove(selectedAppointment);
                    new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted successfully!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete appointment: " + selectedAppointment.getAppId()).show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting customer(s): " + e.getMessage()).show();
            }
        }
    }
    public void onTime(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.TIME, txtTime);
    }

    public void onAppointment(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.APPID, txtAppointment);
    }

}