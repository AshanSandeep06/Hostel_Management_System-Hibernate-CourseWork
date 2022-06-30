package lk.ijse.hibernate.bo;

import lk.ijse.hibernate.bo.custom.impl.*;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        LOGIN, ADMINDASHBOARDBO, RECEPTIONISTDASHBOARDBO,CHANGEPASSWORDBO,CHECKROOMSAVAILABILITYBO, HOMEPAGEBO, ROOM, STUDENT, REGISTRATIONBO, FINDREMAINKEYMONEYSTUDENTSBO, VIEWSTUDENTREGISTRATIONDETAIL
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case LOGIN:
                return new LoginBOImpl();
            case ADMINDASHBOARDBO:
                return new AdminDashBoardBOImpl();
            case RECEPTIONISTDASHBOARDBO:
                return new ReceptionistDashBoardBOImpl();
            case CHANGEPASSWORDBO:
                return new ChangePasswordBOImpl();
            case CHECKROOMSAVAILABILITYBO:
                return new CheckRoomsAvailabilityBOImpl();
            case HOMEPAGEBO:
                return new HomePageBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case REGISTRATIONBO:
                return new RegistrationBOImpl();
            case FINDREMAINKEYMONEYSTUDENTSBO:
                return new FindRemainKeyMoneyStudentsBOImpl();
            case VIEWSTUDENTREGISTRATIONDETAIL:
                return new ViewStudentRegistrationDetailBOImpl();
            default:
                return null;
        }
    }
}
