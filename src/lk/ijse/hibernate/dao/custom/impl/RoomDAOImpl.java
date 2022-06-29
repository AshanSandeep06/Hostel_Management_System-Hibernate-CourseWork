package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Room");
        ArrayList<Room> allRooms = new ArrayList<>();

        List<Room> list = query.list();
        /*Iterator<Room> itr = list.iterator();
        while(itr.hasNext()){
            allRooms.add(itr.next());
        }*/

        for (Room room : list) {
            allRooms.add(room);
        }

        transaction.commit();
        session.close();
        return allRooms;
    }

    @Override
    public boolean save(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String roomTypeID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, roomTypeID);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String roomTypeID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Room WHERE room_type_id=:i");
        query.setParameter("i",roomTypeID);

        List<Room> list = query.list();

        transaction.commit();
        session.close();
        Iterator<Room> itr = list.iterator();
        if (itr.hasNext()) {
            return itr.next();
        }
        return null;
    }

    @Override
    public boolean isExists(String roomTypeID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT room_type_id FROM Room WHERE room_type_id=:i");
        query.setParameter("i",roomTypeID);

        List<String> list = query.list();

        transaction.commit();
        session.close();
        Iterator<String> itr = list.iterator();
        return itr.hasNext();
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }
}
