package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
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
public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();

        transaction.commit();
        session.close();
        Iterator<Student> itr = list.iterator();
        ArrayList<Student> allStudents = new ArrayList<>();
        while (itr.hasNext()){
            allStudents.add(itr.next());
        }
        return allStudents;
    }

    @Override
    public boolean save(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String studentID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, studentID);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String studentID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student WHERE student_id=:sId";
        Query query = session.createQuery(hql);
        query.setParameter("sId",studentID);
        List<Student> list = query.list();

        transaction.commit();
        session.close();
        Iterator<Student> itr = list.iterator();
        if(itr.hasNext()){
            return itr.next();
        }
        return null;
    }

    @Override
    public boolean isExists(String studentID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT student_id FROM Student WHERE student_id=:sId";
        Query query = session.createQuery(hql);
        query.setParameter("sId",studentID);
        List<String> list = query.list();
        Iterator<String> itr = list.iterator();

        transaction.commit();
        session.close();
        return itr.hasNext();
    }

    @Override
    public String generateNewId() throws Exception {
        return null;
    }
}
