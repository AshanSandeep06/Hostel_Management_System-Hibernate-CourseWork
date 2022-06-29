package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.LoginDTO;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface LoginBO extends SuperBO {
    boolean saveLoginAccess(LoginDTO dto) throws Exception;

    LoginDTO getLoginAccess(String userName, String password) throws Exception;
}
