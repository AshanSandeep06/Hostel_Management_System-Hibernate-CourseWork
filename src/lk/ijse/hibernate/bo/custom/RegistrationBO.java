package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface RegistrationBO extends SuperBO {
    StudentDTO searchStudent(String studentID) throws Exception;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    ArrayList<RoomDTO> getAllRooms() throws Exception;

    String generateNewReservationId() throws Exception;

    boolean reservationIsExists(String reservationID) throws Exception;

    boolean makeRegistration(ReservationDTO reservationDTO) throws Exception;
}
