package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RegistrationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class RegistrationBOImpl implements RegistrationBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

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

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student entity : studentDAO.getAll()) {
            allStudents.add(new StudentDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact_no(),
                    entity.getDob(),
                    entity.getGender()
            ));
        }
        return allStudents;
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws Exception {
        ArrayList<RoomDTO> allRooms = new ArrayList<>();
        for (Room entity : roomDAO.getAll()) {
            allRooms.add(new RoomDTO(
                    entity.getRoom_type_id(),
                    entity.getType(),
                    entity.getKey_money(),
                    entity.getQty()
            ));
        }
        return allRooms;
    }

    @Override
    public String generateNewReservationId() throws Exception {
        String reservationId = reservationDAO.generateNewId();
        String finalId = "R00001";

        if (reservationId != null) {
            String[] splitString = reservationId.split("R");
            int id = Integer.parseInt(splitString[1]);
            id++;

            if (id < 10) {
                finalId = "R0000" + id;
            } else if (id < 100) {
                finalId = "R000" + id;
            } else if (id < 1000) {
                finalId = "R00" + id;
            } else if (id < 10000) {
                finalId = "R0" + id;
            } else {
                finalId = "R" + id;
            }
            return finalId;
        } else {
            return finalId;
        }
    }

    @Override
    public boolean reservationIsExists(String reservationID) throws Exception {
        return reservationDAO.isExists(reservationID);
    }

    @Override
    public boolean makeRegistration(ReservationDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Reservation reservation = new Reservation(
                    dto.getRes_id(),
                    dto.getArrival_date(),
                    dto.getDeparture_date(),
                    dto.getPaid_key_money(),
                    dto.getRemain_key_money(),
                    dto.getStatus(),
                    session.get(Student.class, dto.getStudent_id()),
                    session.get(Room.class, dto.getRoom_type_id()));
//                    new Student(dto.getStudent_id()),
//                    new Room(dto.getRoom_type_id()));

            transaction.commit();
            session.close();

            boolean reservationIsSaved = reservationDAO.save(reservation);
            if (!reservationIsSaved) {
                return false;
            }
            Room room = roomDAO.search(dto.getRoom_type_id());
            room.setQty(room.getQty() - 1);

            boolean roomQtyUpdated = roomDAO.update(room);
            if (!roomQtyUpdated) {
                return false;
            }
//            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
//            session.close();
        }
    }
}
