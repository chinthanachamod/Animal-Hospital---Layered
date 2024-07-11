package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;
public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DaoType{
        APPOINMENT,MEDICINE,MEDICINE_SUPPLIER,PET,PET_OWNER,PRESCRIPTION,SUPPLIER,VETERINARIAN,PET_MED_DETAIL,JOIN_QUERY
    }
    public SuperDAO getInstance(DaoType daoType){
        switch (daoType){
            case APPOINMENT:
                return new AppoinmentDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case MEDICINE_SUPPLIER:
                return new MedicineSupplierDAOImpl();
            case PET:
                return new PetDAOImpl();
            case PET_OWNER:
                return new PetOwnerDAOImpl();
            case PRESCRIPTION:
                return new PrescriptionDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case VETERINARIAN:
                return new VeterinaringDAOImpl();
            case PET_MED_DETAIL:
                return new PetMediDAOImpl();
            default:
                return null;
        }
    }
}
