package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.LoginDAO;
import lk.ijse.hibernate.entity.Login;

import java.util.ArrayList;

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
        return false;
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
}
