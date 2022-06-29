package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.JoinQueryDAO;
import lk.ijse.hibernate.entity.CustomEntity;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
}
