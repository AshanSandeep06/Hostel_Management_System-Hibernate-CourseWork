package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Reservation search(String s) throws Exception {
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
