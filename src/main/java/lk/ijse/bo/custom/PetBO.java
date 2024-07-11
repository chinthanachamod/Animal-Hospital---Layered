package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PetDTO;

import java.sql.SQLException;
import java.util.List;

public interface PetBO extends SuperBO {
    public boolean savePet(PetDTO obj) throws SQLException, NullPointerException ;
    public boolean updatePet(PetDTO obj) throws SQLException, NullPointerException ;
    public boolean deletePet(String id) throws SQLException, NullPointerException ;
    public List<String> getPetValue(String... value) throws SQLException, NullPointerException ;
    public List<PetDTO> getAllPet() throws SQLException, NullPointerException ;
    public PetDTO petSearchById(String value) throws SQLException, NullPointerException ;
}
