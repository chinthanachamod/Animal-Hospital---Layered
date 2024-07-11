package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AppoinmentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AppoinmentDAO;
import lk.ijse.dao.custom.impl.AppoinmentDAOImpl;
import lk.ijse.dto.AppointmentDTO;
import lk.ijse.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoinmentBOImpl implements AppoinmentBO {
    AppoinmentDAO appoinmentDAO = (AppoinmentDAO) DAOFactory.getDaoFactory().getInstance(DAOFactory.DaoType.APPOINMENT);
    @Override
    public boolean saveAppoinment(AppointmentDTO appointment) throws SQLException {
        return appoinmentDAO.save(new Appointment(appointment.getAppId(),appointment.getDate(),appointment.getTime(),appointment.getPetOwId(),appointment.getVetId()));
    }

    @Override
    public AppointmentDTO appoinmentSearchById(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateAppoinment(AppointmentDTO appointment) throws SQLException {
        return appoinmentDAO.update(new Appointment(appointment.getAppId(),appointment.getDate(),appointment.getTime(),appointment.getPetOwId(),appointment.getVetId()));
    }

    @Override
    public boolean deleteAppoinment(String id) throws SQLException {
        return appoinmentDAO.delete(id);
    }

    @Override
    public List<String> getAppoinmentValue(String... value) throws SQLException, NullPointerException {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllAppoinment() throws SQLException {
        List<Appointment> list = appoinmentDAO.getAll();
        List<AppointmentDTO> appoinmentList = new ArrayList<>();
        for (Appointment appointment : list){
            appoinmentList.add(new AppointmentDTO(
                    appointment.getAppId(),
                    appointment.getDate(),
                    appointment.getTime(),
                    appointment.getPetOwId(),
                    appointment.getVetId()
            ));
        }
        return appoinmentList;
    }
}
