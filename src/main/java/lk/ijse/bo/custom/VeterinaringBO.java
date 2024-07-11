package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.VeterinarianDTO;

import java.sql.SQLException;
import java.util.List;

public interface VeterinaringBO extends SuperBO {
    public boolean saveVeterinaring(VeterinarianDTO veterinarian) throws SQLException ;
    public VeterinarianDTO veterinaringSearchById(String id) throws SQLException ;
    public boolean updateVeterinaring(VeterinarianDTO veterinarian) throws SQLException ;
    public boolean deleteVeterinaring(String id) throws SQLException ;
    public List<String> getVeterinaringValue(String... value) throws SQLException, NullPointerException ;
    public List<VeterinarianDTO> getAllVeterinaring() throws SQLException ;
    public boolean checkCredintial(String username, String password) throws SQLException;
}
