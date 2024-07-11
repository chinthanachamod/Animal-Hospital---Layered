package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SqlUtil;
import lk.ijse.dao.custom.PetDAO;
import lk.ijse.entity.Pet;
import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    @Override
    public boolean save(Pet obj) throws SQLException, NullPointerException {
        return SqlUtil.executeSql("INSERT INTO Pet VALUES(?,?,?,?,?)",obj.getPetId(),obj.getAge(),obj.getWeight(),obj.getBreed(),obj.getPetOwId());
    }
    @Override
    public boolean update(Pet obj) throws SQLException, NullPointerException {
        return SqlUtil.executeSql("UPDAtE Pet SET  age = ?, weight = ?, breed = ?, petOwId = ? WHERE petId =?",obj.getAge(),obj.getWeight(),obj.getBreed(),obj.getPetOwId(),obj.getPetId());
    }
    @Override
    public boolean delete(String id) throws SQLException, NullPointerException {
        return SqlUtil.executeSql("DELETE FROM Pet WHERE petId = ?",id);
    }
    @Override
    public List<String> getValue(String... value) throws SQLException, NullPointerException {
       ResultSet rst = SqlUtil.executeSql("SELECT petId FROM Pet");
       List<String> idList = new ArrayList<>();
       while (rst.next()){
           idList.add(rst.getString("petId"));
       }
       return idList;
    }
    @Override
    public List<Pet> getAll() throws SQLException, NullPointerException {
        ResultSet rst = SqlUtil.executeSql("SELECT * FROM Pet");
        List<Pet> petList = new ArrayList<>();
        while (rst.next()){
            petList.add(new Pet(
                    rst.getString(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
                    ));
        }
        return petList;
    }
    @Override
    public Pet searchById(String value) throws SQLException, NullPointerException {
        return null;
    }

}