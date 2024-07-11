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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.bo.custom.impl.MedicineBOImpl;
import lk.ijse.dto.MedicineDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MedicineController {
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnBack;
    @FXML
    private AnchorPane MainAnchorpane;
    @FXML
    private JFXTextField txtMedId;
    @FXML
    private JFXTextField txtMedDesc;
    @FXML
    private JFXTextField txtMedQty;
    @FXML
    private TableView<MedicineDTO> tblMedicine;
    @FXML
    private JFXTextField txtMedPrice;
    @FXML
    private TableColumn<?,?>colMedid ;
    @FXML
    private TableColumn<?,?> colDescription;
    @FXML

    private TableColumn<?,?> colQtyOnHand;
    @FXML

    private TableColumn<?,?> colUnitPrice;
    MedicineBO medicineBO = (MedicineBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.MEDICINE);


    public void initialize(){
        setCellFactory();
        loadAllMedicines();

        tblMedicine.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            MedicineDTO selectedItem = tblMedicine.getSelectionModel().getSelectedItem();
            txtMedId.setText(selectedItem.getMedId());
            txtMedDesc.setText(selectedItem.getDescription());
            txtMedQty.setText(String.valueOf(selectedItem.getQty()));
            txtMedPrice.setText(String.valueOf(selectedItem.getPrice()));
        });
    }

    private void loadAllMedicines() {
        try {
            List<MedicineDTO> medicineList = medicineBO.getAllMedicine();
            ObservableList<MedicineDTO> observableList = FXCollections.observableArrayList(medicineList);
            tblMedicine.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactory() {
        colMedid.setCellValueFactory(new PropertyValueFactory<>("medId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void onMedicineBackClick(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
        Stage stage = (Stage) MainAnchorpane.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }

    @FXML
    private void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtMedId.clear();
        txtMedDesc.clear();
        txtMedQty.clear();
        txtMedPrice.clear();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {
        String medId = txtMedId.getText();
        try {
            boolean isDeleted = medicineBO.deleteMedicine(medId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Deleted Successfully!").show();
                loadAllMedicines();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Medicine!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting Medicine: " + e.getMessage()).show();
        }

    }

    @FXML
    private void btnSaveOnAction(ActionEvent actionEvent) {
        String medId = txtMedId.getText();
        String medDesc = txtMedDesc.getText();
        int medQty = Integer.parseInt(txtMedQty.getText());
        double medPrice = Double.parseDouble(txtMedPrice.getText());
        MedicineDTO medicine = new MedicineDTO(medId, medDesc, medQty, medPrice);
        try {
            boolean isSaved = medicineBO.saveMedicine(medicine);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Saved Successfully!").show();
                loadAllMedicines();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Medicine!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving Medicine: " + e.getMessage()).show();
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent actionEvent) {
        String medId = txtMedId.getText();
        String medDesc = txtMedDesc.getText();
        int medQty = Integer.parseInt(txtMedQty.getText());
        double medPrice = Double.parseDouble(txtMedPrice.getText());
        MedicineDTO medicine = new MedicineDTO(medId, medDesc, medQty, medPrice);
        try {
            boolean isUpdated = medicineBO.updateMedicine(medicine);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Updated Successfully!").show();
                loadAllMedicines();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Medicine!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating Medicine: " + e.getMessage()).show();
        }
    }

    public void onPrice(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.PRICE, txtMedPrice);
    }

    public void onMedId(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.MEDID, txtMedId);
    }

    public void onDesc(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.DESCRIPTION, txtMedDesc);
    }

    public void onQty(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.QTY, txtMedQty);
    }
}