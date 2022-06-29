package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.CustomEntity;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface JoinQueryDAO extends SuperDAO {
    ArrayList<CustomEntity> loadAllRoomsDetails() throws Exception;
}
