package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.RoomDTO;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface RoomBO  extends SuperBO {
    boolean roomIsExists(String roomTypeID) throws Exception;

    RoomDTO searchRoom(String roomTypeID) throws Exception;

    ArrayList<RoomDTO> getAllRooms() throws Exception;

    boolean saveRoom(RoomDTO dto) throws Exception;

    boolean updateRoom(RoomDTO dto) throws Exception;

    boolean deleteRoom(String roomTypeID) throws Exception;
}
