package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierDTO;
import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public boolean saveSupplier(SupplierDTO supplier) throws SQLException ;
    public SupplierDTO SupplierSearchById(String id) throws SQLException ;
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException ;
    public boolean deleteSupplier(String id) throws SQLException ;
    public List<String> getSupplierValue(String... value) throws SQLException, NullPointerException ;
    public List<SupplierDTO> getAllSupplier() throws SQLException ;
}
