package lk.ijse.Controller;

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
import lk.ijse.bo.custom.PetOwnerBO;
import lk.ijse.bo.custom.impl.PetOwnerBOImpl;
import lk.ijse.dto.PetOwnerDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PetOwnerController {

    @FXML
    private AnchorPane MainAnchorpane;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<PetOwnerDTO> tblPetOwner;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPetOwnerID;
    PetOwnerBO petOwnerBO = (PetOwnerBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PET_OWNER);

    public void initialize() {
        setCellValueFactory();
        loadAllPetOwners();
        tblPetOwner.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                txtPetOwnerID.setText(newSelection.getPetOwId());
                txtName.setText(newSelection.getName());
                txtContact.setText(newSelection.getContactNo());

            }
        });

    }

    private void loadAllPetOwners() {
        ObservableList<PetOwnerDTO> obList = FXCollections.observableArrayList();

        try {
            List<PetOwnerDTO> petOwnerList = petOwnerBO.getAllPetOwner();
            for (PetOwnerDTO petOwner : petOwnerList) {
                PetOwnerDTO tm = new PetOwnerDTO(
                        petOwner.getPetOwId(),
                        petOwner.getName(),
                        petOwner.getContactNo()
                );

                obList.add(tm);
            }

            tblPetOwner.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("petOwId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

    }

    @FXML
    void btnBAckOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
        Stage stage = (Stage) MainAnchorpane.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ObservableList<PetOwnerDTO> selectedPetOwner = tblPetOwner.getSelectionModel().getSelectedItems();
        if (selectedPetOwner.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select PetOwner(s) to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected PetOwner(s)?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            try {
                for (PetOwnerDTO pw : selectedPetOwner) {
                    boolean isDeleted = petOwnerBO.deletePetOwner(pw.getPetOwId());
                    if (isDeleted) {
                        tblPetOwner.getItems().remove(pw);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete PetOwner: " + pw.getName()).show();
                    }
                }
                new Alert(Alert.AlertType.CONFIRMATION, "PetOwner(s) deleted successfully!").show();
                loadAllPetOwners();
                clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while deleting PetOwner(s): " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtPetOwnerID.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();

        PetOwnerDTO pw = new PetOwnerDTO(id,name,contact);
        try {
            boolean isSaved = petOwnerBO.savePetOwner(pw);
            if (isSaved) {

                tblPetOwner.getItems().add(pw);
                new Alert(Alert.AlertType.CONFIRMATION, "PetOwner saved successfully!").show();
                loadAllPetOwners();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save PetOwner!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving PetOwner: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtContact.clear();
        txtName.clear();
        txtPetOwnerID.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtPetOwnerID.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();

        PetOwnerDTO pw = new PetOwnerDTO(id,name,contact);
        try {
            boolean isUpdated = petOwnerBO.updatePetOwner(pw);
            if (isUpdated) {
                PetOwnerDTO selectedItem = tblPetOwner.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedIndex = tblPetOwner.getItems().indexOf(selectedItem);
                    tblPetOwner.getItems().set(selectedIndex, pw);
                    new Alert(Alert.AlertType.CONFIRMATION, "PetOwner updated successfully!").show();
                    loadAllPetOwners();
                    clearFields();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update PetOwner!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating PetOwner: " + e.getMessage()).show();
        }
    }
    public void onPetOwner(KeyEvent keyEvent) {
      //  Regex.setTextColor(lk.ijse.util.TextField.POID, txtPetOwnerID);
    }

    public void onContact(KeyEvent keyEvent) {
        //Regex.setTextColor(lk.ijse.util.TextField.CONTACT, txtContact);
    }

    public void onName(KeyEvent keyEvent) {
        //Regex.setTextColor(TextField.NAME, txtName);
    }
}