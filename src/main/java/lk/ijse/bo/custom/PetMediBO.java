package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PetMediDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface PetMediBO extends SuperBO {
    public  boolean savePetMedi(PetMediDetailDTO ps) throws SQLException ;
    public boolean updatePetMedi(PetMediDetailDTO obj) throws SQLException, NullPointerException ;
    public boolean deletePetMedi(String id) throws SQLException, NullPointerException ;
    public List<String> getPetMediValue(String... value) throws SQLException, NullPointerException ;
    public List<PetMediDetailDTO> getAllPetMedi() throws SQLException ;
    public PetMediDetailDTO petMediSearchById(String value) throws SQLException, NullPointerException ;
}
