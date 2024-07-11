package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MedicineSupplierDAO;
import lk.ijse.entity.MedicineSupplier;

import java.sql.SQLException;
import java.util.List;

public class MedicineSupplierDAOImpl implements MedicineSupplierDAO {
    @Override
    public boolean save(MedicineSupplier obj) throws SQLException, NullPointerException {
        return false;
    }

    @Override
    public boolean update(MedicineSupplier obj) throws SQLException, NullPointerException {
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
    public List<MedicineSupplier> getAll() throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public MedicineSupplier searchById(String value) throws SQLException, NullPointerException {
        return null;
    }
}
