package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.VeterinaringDAO;
import lk.ijse.entity.Veterinarian;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinaringDAOImpl implements VeterinaringDAO {
    @Override
    public boolean save(Veterinarian veterinarian) throws SQLException {
        String sql = "INSERT INTO Veterinarian VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, veterinarian.getVetId());
        pstm.setString(2, veterinarian.getName());
        pstm.setInt(3, veterinarian.getYrsOfExperience());

        return pstm.executeUpdate() > 0;
    }
    @Override
    public Veterinarian searchById(String id) throws SQLException {
        ResultSet rs = SqlUtil.executeSql("SELECT * FROM Veterinarian WHERE vetId = ?",id);
        if (rs.next()) {
            String vetId = rs.getString(1);
            String name = rs.getString(2);
            int yrsOfExperience = rs.getInt(3);

            return new Veterinarian(vetId, name, yrsOfExperience);
        }
        return null;
    }
    @Override
    public boolean update(Veterinarian veterinarian) throws SQLException {
        String sql = "UPDATE Veterinarian SET name = ?, yrsOfExperience = ? WHERE vetId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, veterinarian.getName());
        pstm.setInt(2, veterinarian.getYrsOfExperience());
        pstm.setString(3, veterinarian.getVetId());
        return pstm.executeUpdate() > 0;
    }
    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Veterinarian WHERE vetId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }
    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        ResultSet resultSet = SqlUtil.executeSql("SELECT vetId FROM Veterinarian");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public List<Veterinarian> getAll() throws SQLException {
        String sql = "SELECT * FROM Veterinarian";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<Veterinarian> veterinarianList = new ArrayList<>();
        while (resultSet.next()) {
            String vetId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int yrsOfExperience = resultSet.getInt(3);
            Veterinarian veterinarian = new Veterinarian(vetId, name, yrsOfExperience);
            veterinarianList.add(veterinarian);
        }
        return veterinarianList;
    }
    public List<String> getIds() throws SQLException {
        String sql = "SELECT vetId FROM Veterinarian";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    public boolean check(String username, String password) throws SQLException {
        try {
            //System.out.println("helloooo");

            ResultSet resultSet = SqlUtil.executeSql("SELECT yrsOfExperience FROM Veterinarian WHERE name = ?",username);

            if (resultSet.next()) {
                System.out.println("result Set in");
                String dbPassword = resultSet.getString("yrsOfExperience");
                return dbPassword.equals(password);
            } else {

                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}