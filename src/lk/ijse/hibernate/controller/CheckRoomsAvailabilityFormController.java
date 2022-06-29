package lk.ijse.hibernate.controller;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.CheckRoomsAvailabilityBO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.view.tdm.RoomAvailabilityTM;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class CheckRoomsAvailabilityFormController {
    public TableView<RoomAvailabilityTM> tblRoomAvailability;
    public TableColumn colRoomTypeID;
    public TableColumn colRoomType;
    public TableColumn colKeyMoneyCost;
    public TableColumn colAvailableRoomsQty;
    public TableColumn colUnavailableRoomsQty;

    // Property Injection
    CheckRoomsAvailabilityBO bo = (CheckRoomsAvailabilityBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHECKROOMSAVAILABILITYBO);

    public void initialize(){
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoneyCost.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colAvailableRoomsQty.setCellValueFactory(new PropertyValueFactory<>("available_rooms_qty"));
        colUnavailableRoomsQty.setCellValueFactory(new PropertyValueFactory<>("unavailable_rooms_qty"));
        loadAllRoomDetails();
    }

    private void loadAllRoomDetails() {
        try{
            ArrayList<CustomDTO> list = bo.loadAllRoomsDetails();
            for (CustomDTO dto : list) {
                tblRoomAvailability.getItems().add(new RoomAvailabilityTM(
                        dto.getRoom_type_id(),
                        dto.getType(),
                        dto.getKey_money(),
                        dto.getAvailable_rooms_qty(),
                        dto.getUnavailable_rooms_qty()
                ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }}
