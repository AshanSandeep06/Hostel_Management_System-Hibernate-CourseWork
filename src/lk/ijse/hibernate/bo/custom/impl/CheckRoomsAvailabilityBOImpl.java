package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.CheckRoomsAvailabilityBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.JoinQueryDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.entity.CustomEntity;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class CheckRoomsAvailabilityBOImpl implements CheckRoomsAvailabilityBO {
    // Property Injection
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOINQUERYDAO);

    @Override
    public ArrayList<CustomDTO> loadAllRoomsDetails() throws Exception {
        ArrayList<CustomDTO> arrayList = new ArrayList<>();
        ArrayList<CustomEntity> allRoomsDetails = joinQueryDAO.loadAllRoomsDetails();
        for (CustomEntity entity : allRoomsDetails) {
            arrayList.add(new CustomDTO(
                    entity.getRoom_type_id(),
                    entity.getType(),
                    entity.getKey_money(),
                    entity.getAvailable_rooms_qty(),
                    entity.getUnavailable_rooms_qty()
            ));
        }
        return arrayList;
    }
}
