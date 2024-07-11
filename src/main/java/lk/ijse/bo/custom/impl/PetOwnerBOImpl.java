package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PetOwnerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PetOwnerDAO;
import lk.ijse.dao.custom.impl.PetOwnerDAOImpl;
import lk.ijse.dto.PetOwnerDTO;
import lk.ijse.entity.PetOwner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetOwnerBOImpl implements PetOwnerBO {
    PetOwnerDAO petOwnerDAO = (PetOwnerDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PET_OWNER);
    @Override
    public boolean savePetOwner(PetOwnerDTO petOwner) throws SQLException {
        return petOwnerDAO.save(new PetOwner(petOwner.getPetOwId(), petOwner.getName(), petOwner.getContactNo()));
    }

    @Override
    public PetOwnerDTO petOwnersearchById(String id) throws SQLException {
            PetOwner petOwner = petOwnerDAO.searchById(id);
            return new PetOwnerDTO(petOwner.getPetOwId(), petOwner.getName(), petOwner.getContactNo());

    }

    @Override
    public boolean updatePetOwner(PetOwnerDTO petOwner) throws SQLException {
        return petOwnerDAO.update(new PetOwner(petOwner.getPetOwId(),petOwner.getName(),petOwner.getContactNo()));
    }

    @Override
    public boolean deletePetOwner(String id) throws SQLException {
        return petOwnerDAO.delete(id);
    }

    @Override
    public List<String> getPetOwnerValue(String... value) throws SQLException, NullPointerException {
        return petOwnerDAO.getValue();
    }

    @Override
    public List<PetOwnerDTO> getAllPetOwner() throws SQLException {
        List<PetOwner> petOwnerList = petOwnerDAO.getAll();
        List<PetOwnerDTO> pets = new ArrayList<>();
        for (PetOwner petOwner : petOwnerList){
            pets.add(new PetOwnerDTO(petOwner.getPetOwId(),petOwner.getName(),petOwner.getContactNo()));
        }
        return pets;
    }
}
