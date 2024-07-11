package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.entity.Medicine;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public boolean save(Medicine medicine) throws SQLException {
       return SqlUtil.executeSql("INSERT INTO Medicine VALUES(?,?,?,?)",medicine.getMedId(),medicine.getDescription(),medicine.getQty(),medicine.getPrice());
    }
    @Override
    public  Medicine searchById(String id) throws SQLException {
        ResultSet rs = SqlUtil.executeSql("SELECT * FROM Medicine WHERE medId = ?",id);
        if (rs.next()) {
            String medId = rs.getString(1);
            String description = rs.getString(2);
            int qty = Integer.parseInt(rs.getString(3));
            double price = Double.parseDouble(rs.getString(4));
            return new Medicine(medId, description, qty, price);
        }
        return null;
    }
    @Override
    public boolean update(Medicine medicine) throws SQLException {
        return SqlUtil.executeSql("UPDATE Medicine SET description = ?, qty = ?, price = ? WHERE medId = ?",medicine.getDescription(),medicine.getQty(),medicine.getPrice(),medicine.getMedId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.executeSql("DELETE FROM Medicine WHERE medId = ?",id);
        }

    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeSql("SELECT medId FROM Medicine");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public  List<Medicine> getAll() throws SQLException {
        ResultSet resultSet = SqlUtil.executeSql("SELECT * FROM Medicine");
        List<Medicine> medicineList = new ArrayList<>();
        while (resultSet.next()) {
            String medID = resultSet.getString(1);
            String description = resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));
            double price = Double.parseDouble(resultSet.getString(4));
            Medicine medicine = new Medicine(medID, description, qty, price);
            medicineList.add(medicine);
        }
        return medicineList;
    }

    public static List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeSql("SELECT medId FROM Medicine");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public boolean updateQty(String Id, int qty) throws SQLException {
       return SqlUtil.executeSql("UPDATE Medicine SET qty = qty - ? WHERE medId = ?",qty,Id);
    }
}