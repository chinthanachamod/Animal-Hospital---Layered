package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dbConnection.DbConnection;
import lk.ijse.entity.Veterinarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface VeterinaringDAO extends CrudDAO<Veterinarian> {
    public boolean check(String username, String password) throws SQLException ;
}

