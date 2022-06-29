package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.LoginBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.LoginDAO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.entity.Login;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class LoginBOImpl implements LoginBO {

    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean saveLoginAccess(LoginDTO dto) throws Exception {
        if (dto != null) {
            return loginDAO.save(new Login(dto.getName(),dto.getUserName(), dto.getPassword(), dto.getJobRole()));
        }
        return false;
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
