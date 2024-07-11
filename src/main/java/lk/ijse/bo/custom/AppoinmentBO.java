package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dbConnection.DbConnection;
import lk.ijse.dto.AppointmentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface AppoinmentBO extends SuperBO {
    public boolean saveAppoinment(AppointmentDTO appointment) throws SQLException ;
    public AppointmentDTO appoinmentSearchById(String id) throws SQLException ;
    public boolean updateAppoinment(AppointmentDTO appointment) throws SQLException ;
    public boolean deleteAppoinment(String id) throws SQLException ;
    public List<String> getAppoinmentValue(String... value) throws SQLException, NullPointerException ;
    public List<AppointmentDTO> getAllAppoinment() throws SQLException ;
}
