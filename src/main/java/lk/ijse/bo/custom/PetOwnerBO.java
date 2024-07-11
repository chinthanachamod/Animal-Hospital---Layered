package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dbConnection.DbConnection;
import lk.ijse.dto.PetOwnerDTO;
import lk.ijse.entity.PetOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PetOwnerBO extends SuperBO {
    public  boolean savePetOwner(PetOwnerDTO petOwner) throws SQLException ;
    public PetOwnerDTO petOwnersearchById(String id) throws SQLException ;
    public boolean updatePetOwner(PetOwnerDTO petOwner) throws SQLException ;
    public boolean deletePetOwner(String id) throws SQLException ;
    public List<String> getPetOwnerValue(String... value) throws SQLException, NullPointerException ;
    public List<PetOwnerDTO> getAllPetOwner() throws SQLException ;
}
