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
import lk.ijse.bo.custom.PrescriptionBO;
import lk.ijse.bo.custom.impl.PrescriptionBOImpl;
import lk.ijse.bo.custom.VeterinaringBO;
import lk.ijse.bo.custom.impl.VeterinaringBOImpl;
import lk.ijse.dto.PrescriptionDTO;
import lk.ijse.dto.VeterinarianDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PrescriptionController {
    @FXML
    private TableView<PrescriptionDTO> tblPre;
    @FXML
    private TableColumn <?, ?>colID;

    @FXML
    private TableColumn <?, ?>colVetID;

    @FXML
    private TableColumn <?, ?>colDiangnosis;

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnPrescBack;


    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbPresVetId;

    @FXML
    private JFXTextField txtPrescDiagnos;

    @FXML
    private JFXTextField txtPrescId;
    PrescriptionBO prescriptionBO = (PrescriptionBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PRESCRIPTION);
    VeterinaringBO veterinaringBO = (VeterinaringBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.VETERINARIAN);

    public void initialize() throws SQLException {
        getVetIDs();
        setCellValueFactory();
        loadAllPre();

        tblPre.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cmbPresVetId.setValue(newSelection.getVetId());
                txtPrescDiagnos.setText(newSelection.getDiagnosis());
                txtPrescId.setText(newSelection.getPrescId());

            }
        });
    }

    private void loadAllPre() {
        try {
            List<PrescriptionDTO> prescriptionList = prescriptionBO.getAllPrescription();
            ObservableList<PrescriptionDTO> obList = FXCollections.observableArrayList(prescriptionList);
            tblPre.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading locations: " + e.getMessage(), e);
        }

    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("prescId"));
        colVetID.setCellValueFactory(new PropertyValueFactory<>("vetId"));
        colDiangnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

    }

    public void setVetIDs() throws SQLException {
        List<String> ids =  prescriptionBO.getPrescriptionValue();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String un : ids){
            obList.add(un);
        }
        cmbPresVetId.setItems(obList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtPrescDiagnos.clear();
        txtPrescId.clear();
        colVetID.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ObservableList<PrescriptionDTO> selectedLocations = tblPre.getSelectionModel().getSelectedItems();
        if (selectedLocations.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select Prescription(s) to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected Prescription(s)?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (PrescriptionDTO prescription : selectedLocations) {
                    boolean isDeleted = prescriptionBO.deletePrescription(prescription.getPrescId());
                    if (isDeleted) {
                        tblPre.getItems().remove(prescription);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete location: " + prescription.getPrescId()).show();
                    }
                }
                new Alert(Alert.AlertType.CONFIRMATION, "Location(s) deleted successfully!").show();
                clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting location(s): " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        //TODO > Step 1 - Create save action
        String prescId = txtPrescId.getText();
        String diagnosis= txtPrescDiagnos.getText();
        String vetId = String.valueOf(cmbPresVetId.getValue());

        PrescriptionDTO prescription = new PrescriptionDTO(prescId, diagnosis, vetId);

        try {
            boolean isSaved = prescriptionBO.savePrescription(prescription);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Prescription saved!").show();
                loadAllPre();
                clearFields();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String prescId = txtPrescId.getText();
        String diagnosis= txtPrescDiagnos.getText();
        String vetId = String.valueOf(cmbPresVetId.getValue());

        PrescriptionDTO prescription = new PrescriptionDTO(prescId, diagnosis, vetId);
        try {
            boolean isUpdated = prescriptionBO.updatePrescription(prescription);
            if (isUpdated) {
                int selectedIndex = tblPre.getSelectionModel().getSelectedIndex();
                tblPre.getItems().set(selectedIndex, prescription);
                new Alert(Alert.AlertType.CONFIRMATION, "prescription updated successfully!").show();
                loadAllPre();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update prescription!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating prescription: " + e.getMessage()).show();
        }
    }

    @FXML
    void cmbPresVetIdOnAction(ActionEvent event) {
        String id = cmbPresVetId.getValue();
        try {
            VeterinarianDTO veterinarian = veterinaringBO.veterinaringSearchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getVetIDs() {
        try {
            List<String> idList = veterinaringBO.getVeterinaringValue();
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbPresVetId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onPrescriptionBackClick(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
        Stage stage = (Stage) MainAnchorpane.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }

    public void onPrescription(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PRESID, txtPrescId);
    }

    public void onPrescriptionDisg(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.DESCRIPTION, txtPrescDiagnos);
    }

}