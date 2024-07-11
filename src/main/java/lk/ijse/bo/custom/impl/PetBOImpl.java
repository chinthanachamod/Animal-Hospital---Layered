package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PetBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PetDAO;
import lk.ijse.dao.custom.impl.PetDAOImpl;
import lk.ijse.dto.PetDTO;
import lk.ijse.entity.Pet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetBOImpl implements PetBO {
    PetDAO petDAO = (PetDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PET);
    @Override
    public boolean savePet(PetDTO obj) throws SQLException, NullPointerException {
        return petDAO.save(new Pet(obj.getPetId(),obj.getAge(),obj.getWeight(),obj.getBreed(),obj.getPetOwId()));
    }

    @Override
    public boolean updatePet(PetDTO obj) throws SQLException, NullPointerException {
        return petDAO.update(new Pet(obj.getPetId(), obj.getAge(), obj.getWeight(), obj.getBreed(), obj.getPetOwId()));
    }

    @Override
    public boolean deletePet(String id) throws SQLException, NullPointerException {
        return petDAO.delete(id);
    }

    @Override
    public List<String> getPetValue(String... value) throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public List<PetDTO> getAllPet() throws SQLException, NullPointerException {
        List<Pet> pets = petDAO.getAll();
        List<PetDTO> petList = new ArrayList<>();
        for (Pet pet : pets){
            petList.add(new PetDTO(pet.getPetId(),pet.getAge(),pet.getWeight(),pet.getBreed(),pet.getPetOwId()));
        }
        return petList;
    }

    @Override
    public PetDTO petSearchById(String value) throws SQLException, NullPointerException {
        return null;
    }
}
