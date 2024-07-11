package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.entity.Appointment;
import lk.ijse.dbConnection.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentDAOImpl implements AppoinmentDAO {
    @Override
    public boolean save(Appointment appointment) throws SQLException {
       return SqlUtil.executeSql("INSERT INTO Appointment VALUES(?,?,?,?,?)",appointment.getAppId(),appointment.getDate(),appointment.getTime(),appointment.getPetOwId(),appointment.getVetId());
    }
    @Override
    public Appointment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Appointment WHERE appId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String appId = rs.getString(1);
            java.util.Date date = rs.getDate(2);
            String time = rs.getString(3);
            String petOwId = rs.getString(4);
            String vetId = rs.getString(5);

            return new Appointment(appId, date, time, petOwId, vetId);
        }
        return null;
    }
    @Override
    public boolean update(Appointment appointment) throws SQLException {
        return SqlUtil.executeSql("UPDATE Appointment SET date = ?, time = ?, petOwId = ?, vetId = ? WHERE appId = ?",appointment.getDate(),appointment.getTime(),appointment.getPetOwId(),appointment.getVetId(),appointment.getAppId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.executeSql("DELETE FROM Appointment WHERE appId = ?",id);
    }

    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        return SqlUtil.executeSql("");
    }
    @Override
    public List<Appointment> getAll() throws SQLException {
        ResultSet resultSet = SqlUtil.executeSql("SELECT * FROM Appointment");
        List<Appointment> appointmentList = new ArrayList<>();
        while (resultSet.next()) {
            String appId = resultSet.getString(1);
            java.util.Date date = resultSet.getDate(2);
            String time = resultSet.getString(3);
            String petOwId = resultSet.getString(4);
            String vetId = resultSet.getString(5);
            Appointment appointment = new Appointment(appId, date, time, petOwId, vetId);
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT appId FROM Appointment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}