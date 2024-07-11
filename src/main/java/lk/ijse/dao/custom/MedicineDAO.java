package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Medicine;

import java.sql.SQLException;

public interface MedicineDAO extends CrudDAO<Medicine> {
    public boolean updateQty(String Id, int qty) throws SQLException;
}
