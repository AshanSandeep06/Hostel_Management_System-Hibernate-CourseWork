package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.CustomEntity;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface JoinQueryDAO extends SuperDAO {
    ArrayList<CustomEntity> loadAllRoomsDetails() throws Exception;

    ArrayList<CustomEntity> findAllRemainKeyMoneyStudentDetails() throws Exception;

    ArrayList<CustomEntity> findRemainKeyMoneyDetailsByStudentId(String studentID) throws Exception;

    ArrayList<CustomEntity> getStudentDetailsByRoomTypeId(String roomTypeId) throws Exception;
}
