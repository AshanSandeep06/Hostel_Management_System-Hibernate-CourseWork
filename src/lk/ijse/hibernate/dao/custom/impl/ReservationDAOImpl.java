package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;
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
public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Reservation entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String reservationID) throws Exception {
        return false;
    }

    @Override
    public Reservation search(String reservationID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Reservation WHERE res_id=:i");
        query.setParameter("i",reservationID);

        List<Reservation> list = query.list();

        transaction.commit();
        session.close();
        Iterator<Reservation> itr = list.iterator();
        if (itr.hasNext()) {
            return itr.next();
        }
        return null;
    }

    @Override
    public boolean isExists(String reservationID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT res_id FROM Reservation WHERE res_id=:rId";
        Query query = session.createQuery(hql);
        query.setParameter("rId",reservationID);
        List<String> list = query.list();
        Iterator<String> itr = list.iterator();

        transaction.commit();
        session.close();
        return itr.hasNext();
    }

    @Override
    public String generateNewId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1");
        String reservationID = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return reservationID;
    }
}
