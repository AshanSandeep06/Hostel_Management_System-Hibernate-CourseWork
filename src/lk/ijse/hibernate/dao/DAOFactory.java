package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.custom.impl.*;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        LOGIN, RESERVATION, ROOM, STUDENT, JOINQUERYDAO
    }

    // This Method for hide the object creation logic and return what user wants
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case LOGIN:
                return new LoginDAOImpl();       // SuperDAO superDAO=new LoginDAOImpl();
            // return superDAO;
            case RESERVATION:
                return new ReservationDAOImpl(); // SuperDAO superDAO=new ReservationDAOImpl();
            case ROOM:
                return new RoomDAOImpl();       // SuperDAO superDAO=new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();   // SuperDAO superDAO=new StudentDAOImpl();
            case JOINQUERYDAO:
                return new JoinQueryDAOImpl();  // SuperDAO superDAO=new JoinQueryDAOImpl();
            default:
                return null;
        }
    }
}
