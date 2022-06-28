package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.entity.SuperEntity;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO {
    ArrayList<T> getAll() throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID id) throws Exception;

    T search(ID id) throws Exception;

    boolean isExists(ID id) throws Exception;

    String generateNewId() throws Exception;
}
