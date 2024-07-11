package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineSupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MedicineSupplierDAO;
import lk.ijse.dao.custom.impl.MedicineSupplierDAOImpl;
import lk.ijse.dto.MedicineSupplierDTO;
import lk.ijse.entity.MedicineSupplier;

import java.sql.SQLException;
import java.util.List;
public class MedicineSupplierBOImpl implements MedicineSupplierBO {
    MedicineSupplierDAO medicineSupplierDAO = (MedicineSupplierDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.MEDICINE_SUPPLIER);
    @Override
    public boolean save(MedicineSupplierDTO obj) throws SQLException, NullPointerException {
        return medicineSupplierDAO.save(new MedicineSupplier(obj.getMedicineID(),obj.getSupplierID()));
    }

    @Override
    public boolean update(MedicineSupplierDTO obj) throws SQLException, NullPointerException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, NullPointerException {
        return false;
    }

    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public List<MedicineSupplierDTO> getAll() throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public MedicineSupplierDTO searchById(String value) throws SQLException, NullPointerException {
        return null;
    }
}
