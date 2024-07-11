package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MedicineDTO;

import java.sql.SQLException;
import java.util.List;

public interface MedicineBO extends SuperBO {
    public boolean saveMedicine(MedicineDTO medicine) throws SQLException ;
    public  MedicineDTO MedicinesearchById(String id) throws SQLException ;
    public boolean updateMedicine(MedicineDTO medicine) throws SQLException ;
    public boolean deleteMedicine(String id) throws SQLException ;
    public List<String> getMedicineValue(String... value) throws SQLException, NullPointerException ;
    public  List<MedicineDTO> getAllMedicine() throws SQLException ;
    public boolean updateMedicineQty(String Id, int qty) throws SQLException ;
}
