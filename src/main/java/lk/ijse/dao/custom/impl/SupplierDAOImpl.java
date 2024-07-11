package lk.ijse.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.entity.MedicineSupplier;
import lk.ijse.entity.Supplier;
import lk.ijse.dbConnection.DbConnection;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplier.getSuppId());
        pstm.setString(2, supplier.getName());
        pstm.setString(3, supplier.getContactNo());

        return pstm.executeUpdate() > 0;
    }
    @Override
    public Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE suppId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String suppId = rs.getString(1);
            String name = rs.getString(2);
            String contactNo = rs.getString(3);

            Supplier supplier = new Supplier(suppId, name, contactNo);
            return supplier;
        }
        return null;
    }
    @Override
    public boolean update(Supplier supplier) throws SQLException {
        return SqlUtil.executeSql("UPDATE Supplier SET name = ?, contactNo = ? WHERE suppId = ?",supplier.getName(),supplier.getContactNo(),supplier.getSuppId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.executeSql("DELETE FROM Supplier WHERE suppId = ?",id);
    }
    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        return null;
    }
    @Override
    public List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String suppId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNo = resultSet.getString(3);
            Supplier supplier = new Supplier(suppId, name, contactNo);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT suppId FROM Supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static boolean save2(MedicineSupplier medicineSupplier) throws SQLException {
        String sql = "INSERT INTO PetVaccDetail2 VALUES(?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, medicineSupplier.getSupplierID());
        pstm.setString(2, medicineSupplier.getMedicineID());

        return pstm.executeUpdate() > 0;
    }

}