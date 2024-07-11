package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PrescriptionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PrescriptionDAO;
import lk.ijse.dao.custom.impl.PrescriptionDAOImpl;
import lk.ijse.dto.PrescriptionDTO;
import lk.ijse.entity.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionBOImpl implements PrescriptionBO {
    PrescriptionDAO prescriptionDAO = (PrescriptionDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PRESCRIPTION);
    @Override
    public boolean savePrescription(PrescriptionDTO prescription) throws SQLException {
        return prescriptionDAO.save(new Prescription(prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId()));
    }

    @Override
    public PrescriptionDTO PrescriptionSearchById(String id) throws SQLException {
        Prescription prescription = prescriptionDAO.searchById(id);
        return new PrescriptionDTO(prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId());
    }

    @Override
    public boolean updatePrescription(PrescriptionDTO prescription) throws SQLException {
        return prescriptionDAO.update(new Prescription(prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId()));
    }

    @Override
    public boolean deletePrescription(String id) throws SQLException {
        return prescriptionDAO.delete(id);
    }

    @Override
    public List<String> getPrescriptionValue(String... value) throws SQLException, NullPointerException {
        return prescriptionDAO.getValue();
    }

    @Override
    public List<PrescriptionDTO> getAllPrescription() throws SQLException {
        List<Prescription> prescriptionList = prescriptionDAO.getAll();
        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        for (Prescription prescription : prescriptionList){
            prescriptionDTOS.add(new PrescriptionDTO(prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId()));
        }
        return prescriptionDTOS;
    }
}
