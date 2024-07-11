package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PetMediBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PetMediDAO;
import lk.ijse.dao.custom.impl.PetMediDAOImpl;
import lk.ijse.dto.PetMediDetailDTO;
import lk.ijse.entity.PetMediDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetMediBOImpl implements PetMediBO {
    PetMediDAO petMediDAO = (PetMediDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.PET_MED_DETAIL);
    @Override
    public boolean savePetMedi(PetMediDetailDTO ps) throws SQLException {
        return petMediDAO.save(new PetMediDetail(ps.getMedicine_ID(),ps.getP_ID(),ps.getQTY()));
    }

    @Override
    public boolean updatePetMedi(PetMediDetailDTO obj) throws SQLException, NullPointerException {
        return false;
    }

    @Override
    public boolean deletePetMedi(String id) throws SQLException, NullPointerException {
        return false;
    }

    @Override
    public List<String> getPetMediValue(String... value) throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public List<PetMediDetailDTO> getAllPetMedi() throws SQLException {
        List<PetMediDetail> petMediDetails = petMediDAO.getAll();
        List<PetMediDetailDTO> petMediDetailDTOS = new ArrayList<>();
        for (PetMediDetail petMediDetail : petMediDetails){
            petMediDetailDTOS.add(new PetMediDetailDTO(petMediDetail.getMedicine_ID(),petMediDetail.getP_ID(),petMediDetail.getQTY()));
        }
        return petMediDetailDTOS;
    }

    @Override
    public PetMediDetailDTO petMediSearchById(String value) throws SQLException, NullPointerException {
        return null;
    }
}
