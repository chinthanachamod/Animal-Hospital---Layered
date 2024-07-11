package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.dao.custom.impl.MedicineDAOImpl;
import lk.ijse.dto.MedicineDTO;
import lk.ijse.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {
    MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.MEDICINE);
    @Override
    public boolean saveMedicine(MedicineDTO medicine) throws SQLException {
        return medicineDAO.save(new Medicine(medicine.getMedId(),medicine.getDescription(),medicine.getQty(),medicine.getPrice()));
    }

    @Override
    public MedicineDTO MedicinesearchById(String id) throws SQLException {
        Medicine medicine = medicineDAO.searchById(id);
        return new MedicineDTO(medicine.getMedId(), medicine.getDescription(), medicine.getQty(), medicine.getPrice());
    }

    @Override
    public boolean updateMedicine(MedicineDTO medicine) throws SQLException {
        return medicineDAO.update(new Medicine(medicine.getMedId(),medicine.getDescription(),medicine.getQty(),medicine.getPrice()));
    }

    @Override
    public boolean deleteMedicine(String id) throws SQLException {
        return medicineDAO.delete(id);
    }

    @Override
    public List<String> getMedicineValue(String... value) throws SQLException, NullPointerException {
        return medicineDAO.getValue();
    }

    @Override
    public List<MedicineDTO> getAllMedicine() throws SQLException {
        List<Medicine> medicines = medicineDAO.getAll();
        List<MedicineDTO> medicineList = new ArrayList<>();
        for (Medicine medicine : medicines){
            medicineList.add(new MedicineDTO(
                    medicine.getMedId(),
                    medicine.getDescription(),
                    medicine.getQty(),
                    medicine.getPrice()
            ));
        }
        return medicineList;
    }

    @Override
    public boolean updateMedicineQty(String Id, int qty) throws SQLException {
        return false;
    }
}
