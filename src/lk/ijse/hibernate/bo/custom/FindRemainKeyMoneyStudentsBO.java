package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
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

public interface FindRemainKeyMoneyStudentsBO extends SuperBO {

    ArrayList<CustomDTO> loadAllRemainKeyMoneyStudentDetails() throws Exception;

    ReservationDTO searchReservation(String reservationID) throws Exception;

    ArrayList<CustomDTO> findRemainKeyMoneyDetails(String studentID) throws Exception;

    StudentDTO searchStudent(String studentID) throws Exception;
}
