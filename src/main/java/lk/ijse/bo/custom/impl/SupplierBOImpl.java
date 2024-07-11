package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.SUPPLIER);
    @Override
    public boolean saveSupplier(SupplierDTO supplier) throws SQLException {
        return false;
    }

    @Override
    public SupplierDTO SupplierSearchById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
        return supplierDAO.update(new Supplier(supplier.getSuppId(),supplier.getName(),supplier.getContactNo()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public List<String> getSupplierValue(String... value) throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (Supplier supplier : suppliers){
            supplierDTOList.add(new SupplierDTO(supplier.getSuppId(),supplier.getName(),supplier.getContactNo()));
        }
        return supplierDTOList;
    }
}
