package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ViewStudentRegistrationDetailBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.JoinQueryDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.CustomEntity;
import lk.ijse.hibernate.entity.Room;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class ViewStudentRegistrationDetailBOImpl implements ViewStudentRegistrationDetailBO {
    // Property Injection
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERYDAO);

    @Override
    public RoomDTO searchRoom(String roomTypeId) throws Exception {
        Room entity = roomDAO.search(roomTypeId);
        if (entity != null) {
            return new RoomDTO(entity.getRoom_type_id(), entity.getType(), entity.getKey_money(), entity.getQty());
        }
        return null;
    }

    @Override
    public boolean roomIsExists(String roomTypeID) throws Exception {
        return roomDAO.isExists(roomTypeID);
    }

    @Override
    public ArrayList<CustomDTO> getStudentDetailsByRegisterRoomTypeId(String roomTypeId) throws Exception {
        ArrayList<CustomDTO> arrayList = new ArrayList<>();
        for (CustomEntity entity : joinQueryDAO.getStudentDetailsByRoomTypeId(roomTypeId)) {
            arrayList.add(new CustomDTO(
                    entity.getRes_id(),
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact_no(),
                    entity.getDob(),
                    entity.getGender(),
                    entity.getArrival_date(),
                    entity.getDeparture_date()
            ));
        }
        return arrayList;
    }
}
