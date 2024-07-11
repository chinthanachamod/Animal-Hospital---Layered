package lk.ijse.bo;


import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BoType{
        APPOINMENT,MEDICINE,MEDICINE_SUPPLIER,PET,PET_OWNER,PRESCRIPTION,SUPPLIER,VETERINARIAN,PET_MED_DETAIL,JOIN_QUERY
    }
    public SuperBO getInstance(BoType boType){
        switch (boType){
            case APPOINMENT:
                return new AppoinmentBOImpl();
            case MEDICINE:
                return new MedicineBOImpl();
            case MEDICINE_SUPPLIER:
                return new MedicineSupplierBOImpl();
            case PET:
                return new PetBOImpl();
            case PET_OWNER:
                return new PetOwnerBOImpl();
            case PRESCRIPTION:
                return new PrescriptionBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case VETERINARIAN:
                return new VeterinaringBOImpl();
            case PET_MED_DETAIL:
                return new PetMediBOImpl();
            default:
                return null;
        }
    }
}
