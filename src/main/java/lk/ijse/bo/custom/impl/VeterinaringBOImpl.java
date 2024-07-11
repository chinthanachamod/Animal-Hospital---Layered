package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VeterinaringBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.VeterinaringDAO;
import lk.ijse.dao.custom.impl.VeterinaringDAOImpl;
import lk.ijse.dto.VeterinarianDTO;
import lk.ijse.entity.Veterinarian;

import java.sql.SQLException;
import java.util.List;

public class VeterinaringBOImpl implements VeterinaringBO {
    VeterinaringDAO veterinaringDAO = (VeterinaringDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.VETERINARIAN);
    @Override
    public boolean saveVeterinaring(VeterinarianDTO veterinarian) throws SQLException {
        return false;
    }

    @Override
    public VeterinarianDTO veterinaringSearchById(String id) throws SQLException {
        Veterinarian veterinarian = veterinaringDAO.searchById(id);
        return new VeterinarianDTO(veterinarian.getVetId(),veterinarian.getName(),veterinarian.getYrsOfExperience());
    }

    @Override
    public boolean updateVeterinaring(VeterinarianDTO veterinarian) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteVeterinaring(String id) throws SQLException {
        return false;
    }

    @Override
    public List<String> getVeterinaringValue(String... value) throws SQLException, NullPointerException {
        return veterinaringDAO.getValue();
    }

    @Override
    public List<VeterinarianDTO> getAllVeterinaring() throws SQLException {
        return null;
    }

    @Override
    public boolean checkCredintial(String username, String password) throws SQLException {
       return veterinaringDAO.check(username,password);
    }
}
