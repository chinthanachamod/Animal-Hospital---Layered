package lk.ijse.dao.custom.impl;


import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.PrescriptionDAO;
import lk.ijse.entity.Prescription;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public boolean save(Prescription prescription) throws SQLException {
        return SqlUtil.executeSql("INSERT INTO Prescription VALUES(?,?,?)",prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId());
    }
    @Override
    public Prescription searchById(String id) throws SQLException {
        ResultSet rs = SqlUtil.executeSql("SELECT * FROM Prescription WHERE prescId = ?",id);
        if (rs.next()){
            String P_id = rs.getString(1);
            String Diagnosis = rs.getString(2);
            String VetID = rs.getString(3);


            Prescription prescription = new Prescription(P_id,Diagnosis,VetID);

            return prescription;

        }
        return null;

    }
    @Override
    public boolean update(Prescription prescription) throws SQLException {
        return SqlUtil.executeSql("UPDATE Prescription SET diagnosis = ?, vetId = ? WHERE prescId = ?",prescription.getPrescId(),prescription.getDiagnosis(),prescription.getVetId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SqlUtil.executeSql("DELETE FROM Prescription WHERE prescId = ?",id);
    }
    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeSql("SELECT prescId FROM Prescription");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public List<Prescription> getAll() throws SQLException {
        ResultSet resultSet = SqlUtil.executeSql("SELECT * FROM Prescription");
        List<Prescription> pList = new ArrayList<>();
        while (resultSet.next()) {
            String P_id = resultSet.getString(1);
            String Diagnosis = resultSet.getString(2);
            String VetID = resultSet.getString(3);
            Prescription prescription = new Prescription(P_id,Diagnosis,VetID);
            pList.add(prescription);
        }
        return pList;
    }
}



