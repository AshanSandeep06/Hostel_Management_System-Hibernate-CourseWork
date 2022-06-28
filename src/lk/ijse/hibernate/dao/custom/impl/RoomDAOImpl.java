package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.entity.Room;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Room entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Room search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean isExists(String s) throws Exception {
        return false;
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }
}
