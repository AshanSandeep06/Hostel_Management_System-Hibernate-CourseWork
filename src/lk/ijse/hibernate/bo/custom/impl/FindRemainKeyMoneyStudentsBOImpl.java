package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.FindRemainKeyMoneyStudentsBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.JoinQueryDAO;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.CustomEntity;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class FindRemainKeyMoneyStudentsBOImpl implements FindRemainKeyMoneyStudentsBO {
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERYDAO);

    @Override
    public ArrayList<CustomDTO> loadAllRemainKeyMoneyStudentDetails() throws Exception {
        ArrayList<CustomDTO> arrayList = new ArrayList<>();
        for (CustomEntity entity : joinQueryDAO.findAllRemainKeyMoneyStudentDetails()) {
            arrayList.add(new CustomDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getRes_id(),
                    entity.getRoom_type_id(),
                    entity.getPaid_key_money(),
                    entity.getRemain_key_money(),
                    entity.getArrival_date(),
                    entity.getDeparture_date(),
                    entity.getStatus()
            ));
        }
        return arrayList;
    }

    @Override
    public ReservationDTO searchReservation(String reservationID) throws Exception {
        Reservation entity = reservationDAO.search(reservationID);
        if(entity!=null){
            return new ReservationDTO(
                    entity.getRes_id(),
                    entity.getArrival_date(),
                    entity.getDeparture_date(),
                    entity.getStudent().getStudent_id(),
                    entity.getRoom().getRoom_type_id(),
                    entity.getPaid_key_money(),
                    entity.getRemain_key_money(),
                    entity.getStatus()
            );
        }
        return null;
    }

    @Override
    public ArrayList<CustomDTO> findRemainKeyMoneyDetails(String studentID) throws Exception {
        ArrayList<CustomDTO> arrayList = new ArrayList<>();
        for (CustomEntity entity : joinQueryDAO.findRemainKeyMoneyDetailsByStudentId(studentID)) {
            arrayList.add(new CustomDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getRes_id(),
                    entity.getRoom_type_id(),
                    entity.getPaid_key_money(),
                    entity.getRemain_key_money(),
                    entity.getArrival_date(),
                    entity.getDeparture_date(),
                    entity.getStatus()
            ));
        }
        return arrayList;
    }

    @Override
    public StudentDTO searchStudent(String studentID) throws Exception {
        Student entity = studentDAO.search(studentID);
        if (entity != null) {
            return new StudentDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact_no(),
                    entity.getDob(),
                    entity.getGender()
            );
        }
        return null;
    }
}
