package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MedicineSupplierDTO;
import lk.ijse.entity.MedicineSupplier;

import java.sql.SQLException;
import java.util.List;

public interface MedicineSupplierBO extends SuperBO {
    public boolean save(MedicineSupplierDTO obj) throws SQLException, NullPointerException ;
    public boolean update(MedicineSupplierDTO obj) throws SQLException, NullPointerException ;
    public boolean delete(String id) throws SQLException, NullPointerException ;
    public List<String> getValue(String... value) throws SQLException, NullPointerException ;
    public List<MedicineSupplierDTO> getAll() throws SQLException, NullPointerException ;
    public MedicineSupplierDTO searchById(String value) throws SQLException,NullPointerException;
}
