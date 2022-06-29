package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ChangePasswordBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.LoginDAO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.entity.Login;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class ChangePasswordBOImpl implements ChangePasswordBO {
    //Property Injection
    private LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean changeUsernameAndPassword(String currentUserName,String newUserName, String newPassword) throws Exception{
        return loginDAO.changeUsernameAndPasswordByCurrentUsername(currentUserName,newUserName,newPassword);
    }

    @Override
    public LoginDTO getLoginAccess(String userName, String password) throws Exception {
        Login entity = loginDAO.getLogin(userName, password);
        if(entity!=null){
            return new LoginDTO(entity.getName(),entity.getUserName(), entity.getPassword(), entity.getJobRole());
        }
        return null;
    }
}
