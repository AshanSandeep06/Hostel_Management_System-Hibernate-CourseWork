package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Login;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface LoginDAO extends CrudDAO<Login,String> {
    Login getLogin(String userName, String password) throws Exception;

    boolean changeUsernameAndPasswordByCurrentUsername(String currentUserName, String newUserName, String newPassword) throws Exception;
}
