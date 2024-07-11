package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.bo.custom.PetMediBO;
import lk.ijse.bo.custom.PrescriptionBO;
import lk.ijse.bo.custom.impl.MedicineBOImpl;
import lk.ijse.bo.custom.impl.PetMediBOImpl;
import lk.ijse.bo.custom.impl.PrescriptionBOImpl;
import lk.ijse.dbConnection.DbConnection;
import lk.ijse.dto.MedicineDTO;
import lk.ijse.dto.PetMediDetailDTO;
import lk.ijse.dto.PrescriptionDTO;
import lk.ijse.entity.CartTm;
import lk.ijse.entity.PetMediDetail;
import lk.ijse.util.Regex;
import lk.ijse.util.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrescTransfer {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox<String> cmbMedicine;

    @FXML
    private ComboBox<String> cmbPresc;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colM_ID;

    @FXML
    private TableColumn<?, ?> colP_ID;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblMedicine;


    @FXML
    private Label lblMedicineQtyOnHand;

    @FXML
    private Label lblPrescription;

    @FXML
    private TableView<CartTm> tblPM;

    @FXML
    private JFXTextField txtQty;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    PetMediBO petMediBO = (PetMediBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PET_MED_DETAIL);
    MedicineBO medicineBO = (MedicineBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.MEDICINE);
    PrescriptionBO prescriptionBO = (PrescriptionBO) BOFactory.getBoFactory().getInstance(BOFactory.BoType.PRESCRIPTION);

    public void initialize() {
        getPrescID();
        getMedicineID();
        setCellValueFactory();
        LoadAllPresc();
    }

    private void LoadAllPresc() {
        ObservableList<PetMediDetail> obList = FXCollections.observableArrayList();
        try {
            List<PetMediDetailDTO> ps = petMediBO.getAllPetMedi();
            for (PetMediDetailDTO psList : ps) {
                PetMediDetail tm = new PetMediDetail(
                        psList.getMedicine_ID(),
                        psList.getP_ID(),
                        psList.getQTY()
                );
                obList.add(tm);
            }
            // tblPM.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colP_ID.setCellValueFactory(new PropertyValueFactory<>("P_IDC"));
        colM_ID.setCellValueFactory(new PropertyValueFactory<>("Medicine_IDC"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("QTYC"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemoveC"));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String PrescID = cmbPresc.getValue();
        String MedicineID = cmbMedicine.getValue();
        int Qty = Integer.parseInt(txtQty.getText());

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                CartTm selectedIndex =  tblPM.getSelectionModel().getSelectedItem();
                obList.remove(selectedIndex);
                tblPM.refresh();
            }
        });

        CartTm tm = new CartTm( MedicineID,PrescID, Qty, btnRemove);
        obList.add(tm);

        tblPM.setItems(obList);
        txtQty.setText("");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
    }

    private void clearFields() {
        cmbPresc.getSelectionModel().clearSelection();
        cmbMedicine.getSelectionModel().clearSelection();
        lblMedicine.setText("");
        lblMedicineQtyOnHand.setText("");
        lblPrescription.setText("");
        txtQty.clear();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        List<CartTm> toRemove = new ArrayList<>();
        for (CartTm tm : obList) {
            PetMediDetailDTO od = new PetMediDetailDTO(
                    tm.getMedicine_IDC(),
                    tm.getP_IDC(),
                    tm.getQTYC()
            );
            try {
                Connection connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isSaved = petMediBO.savePetMedi(od);
                if (!isSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.ERROR, "Order placed unsuccessfully").show();
                    return;
                }
                    boolean isUpdate = medicineBO.updateMedicineQty(tm.getMedicine_IDC(), tm.getQTYC());
                    if (!isUpdate) {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        new Alert(Alert.AlertType.ERROR,"Order placed unsuccessfully").show();
                        toRemove.add(tm);
                        return;
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.CONFIRMATION, "Place successfully!").show();
                    clearFields();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while Place: " + e.getMessage()).show();
            }
        }
        obList.removeAll(toRemove);
        tblPM.getItems().clear();
    }

    @FXML
    void cmbMedicineOnAction(ActionEvent event) {
        String id = String.valueOf(cmbMedicine.getValue());
        try {
            MedicineDTO medicine = medicineBO.MedicinesearchById(id);
            if (medicine != null) {
                lblMedicine.setText(medicine.getDescription());
                lblMedicineQtyOnHand.setText(String.valueOf(medicine.getQty()));
            } else {
                // Handle case when medicine is not found
                lblMedicine.setText("Medicine not found");
                lblMedicineQtyOnHand.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getMedicineID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = medicineBO.getMedicineValue();
            obList.addAll(idList);
            cmbMedicine.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Medicine IDs: " + e.getMessage());
        }
    }

    private void getPrescID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = prescriptionBO.getPrescriptionValue();
            obList.addAll(idList);
            cmbPresc.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Prescription IDs: " + e.getMessage());
        }
    }
    @FXML
    void cmbPrescOnAction(ActionEvent event) {
        String id = String.valueOf(cmbPresc.getValue());
        try {
            PrescriptionDTO prescription = prescriptionBO.PrescriptionSearchById(id);
            if (prescription != null) {
                lblPrescription.setText(prescription.getDiagnosis());
            } else {
                // Handle case when prescription is not found
                lblPrescription.setText("Prescription not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnMedDetailesOnActionClick(ActionEvent event) {

    }
    public void onQty(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.QTY, txtQty);
    }

}