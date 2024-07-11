package lk.ijse.dao.custom.impl;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.PetMediDAO;
import lk.ijse.entity.PetMediDetail;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetMediDAOImpl implements PetMediDAO {
    @Override
    public  boolean save(PetMediDetail ps) throws SQLException {
        return SqlUtil.executeSql("INSERT INTO PetMedDetail VALUES(?,?,?)",ps.getMedicine_ID(),ps.getP_ID(),ps.getQTY());
    }
    @Override
    public boolean update(PetMediDetail obj) throws SQLException, NullPointerException {
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
    public List<PetMediDetail> getAll() throws SQLException {
        String sql = "SELECT * FROM PetMedDetail";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<PetMediDetail> PSList = new ArrayList<>();

        while (resultSet.next()) {
            String P_ID = resultSet.getString(1);
            String M_Id = resultSet.getString(2);
            int Qty = Integer.parseInt(resultSet.getString(3));


            PetMediDetail ps = new PetMediDetail(P_ID,M_Id,Qty);
            PSList.add(ps);
        }
        return PSList;
    }
    @Override
    public PetMediDetail searchById(String value) throws SQLException, NullPointerException {
        return null;
    }
}