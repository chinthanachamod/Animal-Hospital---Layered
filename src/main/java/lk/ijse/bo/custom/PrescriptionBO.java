package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PrescriptionDTO;
import java.sql.SQLException;
import java.util.List;

public interface PrescriptionBO extends SuperBO {
    public boolean savePrescription(PrescriptionDTO prescription) throws SQLException ;
    public PrescriptionDTO PrescriptionSearchById(String id) throws SQLException ;
    public boolean updatePrescription(PrescriptionDTO prescription) throws SQLException ;
    public boolean deletePrescription(String id) throws SQLException ;
    public List<String> getPrescriptionValue(String... value) throws SQLException, NullPointerException ;
    public List<PrescriptionDTO> getAllPrescription() throws SQLException ;
}
