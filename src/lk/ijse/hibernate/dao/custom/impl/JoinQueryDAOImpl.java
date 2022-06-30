package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.JoinQueryDAO;
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

public class JoinQueryDAOImpl implements JoinQueryDAO {
    @Override
    public ArrayList<CustomEntity> loadAllRoomsDetails() throws Exception {
        // Native SQL Query
        String sql = "SELECT r.room_type_id,r.type,r.key_money,r.qty, COUNT(re.room_room_type_id) as unavailable_qty FROM Room r LEFT JOIN Reservation re ON r.room_type_id = re.room_room_type_id GROUP BY r.room_type_id";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<CustomEntity> arrayList = new ArrayList<>();
        Query query = session.createSQLQuery(sql);
        List<Object[]> list = query.list();

        for (Object[] detail : list) {
            arrayList.add(new CustomEntity(
                    String.valueOf(detail[0]),
                    String.valueOf(detail[1]),
                    String.valueOf(detail[2]),
                    Integer.parseInt(String.valueOf(detail[3])),
                    Integer.parseInt(String.valueOf(detail[4])))
            );
        }

        transaction.commit();
        session.close();
        return arrayList;
    }

    @Override
    public ArrayList<CustomEntity> findAllRemainKeyMoneyStudentDetails() throws Exception {
        // Hibernate Query ---> hql
//        String hql = "SELECT s.student_id, s.name, r.res_id, r.remain_key_money, r.arrival_date, r.departure_date, r.status FROM Student s INNER JOIN Reservation r ON s.student_id=r.student WHERE r.remain_key_money > 10 ORDER BY r.remain_key_money DESC";

        String hql = "SELECT s.student_id, s.name, r.res_id, room.room_type_id, r.paid_key_money, r.remain_key_money, r.arrival_date, r.departure_date, r.status FROM Student s INNER JOIN Reservation r ON s.student_id=r.student INNER JOIN Room room ON room.room_type_id=r.room WHERE r.remain_key_money > 0";

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<CustomEntity> arrayList = new ArrayList<>();
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();

        for (Object[] obj : list) {
            arrayList.add(new CustomEntity(
                    String.valueOf(obj[0]),
                    String.valueOf(obj[1]),
                    String.valueOf(obj[2]),
                    String.valueOf(obj[3]),
                    Double.parseDouble(String.valueOf(obj[4])),
                    Double.parseDouble(String.valueOf(obj[5])),
                    LocalDate.parse(String.valueOf(obj[6])),
                    LocalDate.parse(String.valueOf(obj[7])),
                    String.valueOf(obj[8])
            ));
        }

        transaction.commit();
        session.close();
        return arrayList;
    }

    @Override
    public ArrayList<CustomEntity> findRemainKeyMoneyDetailsByStudentId(String studentID) throws Exception {
        String hql = "SELECT s.student_id, s.name, r.res_id, room.room_type_id, r.paid_key_money, r.remain_key_money, r.arrival_date, r.departure_date, r.status FROM Student s INNER JOIN Reservation r ON s.student_id=r.student INNER JOIN Room room ON room.room_type_id=r.room WHERE s.student_id=:sId AND r.remain_key_money > 0";

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<CustomEntity> arrayList = new ArrayList<>();
        Query query = session.createQuery(hql);
        query.setParameter("sId",studentID);
        List<Object[]> list = query.list();

        for (Object[] obj : list) {
            arrayList.add(new CustomEntity(
                    String.valueOf(obj[0]),
                    String.valueOf(obj[1]),
                    String.valueOf(obj[2]),
                    String.valueOf(obj[3]),
                    Double.parseDouble(String.valueOf(obj[4])),
                    Double.parseDouble(String.valueOf(obj[5])),
                    LocalDate.parse(String.valueOf(obj[6])),
                    LocalDate.parse(String.valueOf(obj[7])),
                    String.valueOf(obj[8])
            ));
        }

        transaction.commit();
        session.close();
        return arrayList;
    }
}
