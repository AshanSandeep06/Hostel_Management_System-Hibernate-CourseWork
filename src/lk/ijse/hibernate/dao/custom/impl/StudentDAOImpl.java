package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Student search(String s) throws Exception {
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
