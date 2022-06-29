package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.LoginDAO;
import lk.ijse.hibernate.entity.Login;
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
public class LoginDAOImpl implements LoginDAO {
    @Override
    public ArrayList<Login> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Login entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Login entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Login search(String s) throws Exception {
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

    @Override
    public Login getLogin(String userName, String password) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Login l WHERE l.userName=:u AND l.password=:p");
        query.setParameter("u", userName);
        query.setParameter("p", password);

        List<Login> list = query.list();

        transaction.commit();
        session.close();
        Iterator<Login> itr = list.iterator();
        if (itr.hasNext()){
            Login entity = itr.next();
            return entity;
        }
        return null;
    }
}
