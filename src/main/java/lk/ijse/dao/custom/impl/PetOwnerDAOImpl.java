package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.PetOwnerDAO;
import lk.ijse.entity.PetOwner;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetOwnerDAOImpl implements PetOwnerDAO {
    @Override
    public  boolean save(PetOwner petOwner) throws SQLException {
        String sql = "INSERT INTO PetOwner VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, petOwner.getPetOwId());
        pstm.setString(2, petOwner.getName());
        pstm.setString(3, petOwner.getContactNo());

        return pstm.executeUpdate() > 0;
    }
    @Override
    public PetOwner searchById(String id) throws SQLException {
        ResultSet rs = SqlUtil.executeSql("SELECT * FROM PetOwner WHERE petOwId = ?",id);
        if (rs.next()) {
            String petOwId = rs.getString(1);
            String name = rs.getString(2);
            String contactNo = rs.getString(3);

            return new PetOwner(petOwId, name, contactNo);
        }
        return null;
    }
    @Override
    public boolean update(PetOwner petOwner) throws SQLException {
        return SqlUtil.executeSql("UPDATE PetOwner SET name = ?, contactNo = ? WHERE petOwId = ?",petOwner.getName(),petOwner.getContactNo(),petOwner.getPetOwId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.executeSql("DELETE FROM PetOwner WHERE petOwId = ?",id);
    }
    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeSql("SELECT petOwId FROM PetOwner");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public List<PetOwner> getAll() throws SQLException {
        ResultSet resultSet = SqlUtil.executeSql("SELECT * FROM PetOwner");
        List<PetOwner> petOwnerList = new ArrayList<>();
        while (resultSet.next()) {
            String petOwId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNo = resultSet.getString(3);
            PetOwner petOwner = new PetOwner(petOwId, name, contactNo);
            petOwnerList.add(petOwner);
        }
        return petOwnerList;
    }

}