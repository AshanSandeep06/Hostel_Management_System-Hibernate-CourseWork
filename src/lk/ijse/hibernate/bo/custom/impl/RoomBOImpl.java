package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.Room;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class RoomBOImpl implements RoomBO {

    //Property Injection
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean roomIsExists(String roomTypeID) throws Exception{
        return roomDAO.isExists(roomTypeID);
    }

    @Override
    public RoomDTO searchRoom(String roomTypeID) throws Exception{
        Room entity = roomDAO.search(roomTypeID);
        if(entity!=null){
            return new RoomDTO(
                    entity.getRoom_type_id(),
                    entity.getType(),
                    entity.getKey_money(),
                    entity.getQty()
            );
        }
        return null;
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws Exception{
        ArrayList<RoomDTO> allRooms = new ArrayList<>();
        for (Room room : roomDAO.getAll()) {
            allRooms.add(new RoomDTO(
                    room.getRoom_type_id(),
                    room.getType(),
                    room.getKey_money(),
                    room.getQty()
            ));
        }
        return allRooms;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws Exception{
        return roomDAO.save(new Room(
                dto.getRoom_type_id(),
                dto.getType(),
                dto.getKey_money(),
                dto.getQty()
        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws Exception{
        return roomDAO.update(new Room(
                dto.getRoom_type_id(),
                dto.getType(),
                dto.getKey_money(),
                dto.getQty()
        ));
    }

    @Override
    public boolean deleteRoom(String roomTypeID) throws Exception{
        return roomDAO.delete(roomTypeID);
    }
}
