package lk.ijse.hibernate.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.util.ValidationUtil;
import lk.ijse.hibernate.view.tdm.RoomTM;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class ManageRoomFormController {
    public JFXButton btnSearch;
    public JFXTextField txtKeyMoneyCost;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public TableView<RoomTM> tblRoom;
    public TableColumn colRoomTypeID;
    public TableColumn colRoomType;
    public TableColumn colKeyMoneyCost;
    public TableColumn colRoomsQty;
    public JFXButton btnAddNewRoom;
    public JFXTextField txtRoomType;
    public JFXButton btnAdd;
    public TextField txtSearchRoom;
    public JFXTextField txtRoomsQty;
    public JFXTextField txtRoomTypeID;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    //Property Injection
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize() {
        btnSearch.setDisable(true);
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoneyCost.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colRoomsQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        storeValidations();
        loadAllRooms();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, room) -> {
            if (room != null) {
                if (!tblRoom.getItems().isEmpty()) {
                    btnUpdate.setDisable(false);
                    btnRemove.setDisable(false);
                    setFontColor(txtRoomTypeID, txtRoomType, txtKeyMoneyCost, txtRoomsQty);
                    setRoomData(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
                }
            }
        });
    }

    private void setFontColor(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-text-fill: BLACK");
        }
    }

    private void storeValidations() {
        map.put(txtRoomTypeID, Pattern.compile("^(RM-)[0-9]{4}$"));
        map.put(txtRoomType, Pattern.compile("^[A-z-/ ]{2,20}$"));
        map.put(txtKeyMoneyCost, Pattern.compile("^[1-9][0-9]{0,8}([.][0-9]{2})?$"));
        map.put(txtRoomsQty, Pattern.compile("^[0-9]{1,4}$"));
    }

    private void setRoomData(RoomDTO dto) {
        txtRoomTypeID.setText(dto.getRoom_type_id());
        txtRoomType.setText(dto.getType());
        txtKeyMoneyCost.setText(dto.getKey_money());
        txtRoomsQty.setText(String.valueOf(dto.getQty()));
    }

    private void loadAllRooms() {
        tblRoom.getItems().clear();
        try {
            ArrayList<RoomDTO> allRooms = roomBO.getAllRooms();
            for (RoomDTO room : allRooms) {
                tblRoom.getItems().add(new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void searchRoom() {
        try {
            if (!txtSearchRoom.getText().isEmpty()) {
                String roomTypeID = txtSearchRoom.getText();
                if (roomTypeID.matches("^(RM-)[0-9]{4}$")) {
                    RoomDTO roomDTO = roomBO.searchRoom(roomTypeID);

                    if (roomDTO != null) {
                        setRoomData(roomDTO);
                        btnUpdate.setDisable(false);
                        btnRemove.setDisable(false);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "No rooms exists for this RoomTypeID..!", ButtonType.OK).show();
                        clearAll();
                    }
                } else {
                    clearAll();
                    new Alert(Alert.AlertType.WARNING, "Invalid Room ID ..!").show();
                }
            } else {
                clearAll();
                new Alert(Alert.AlertType.WARNING, "Empty field,try again..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        btnAdd.setDisable(true);
    }

    public void searchRoomOnAction(ActionEvent event) {
        searchRoom();
    }

    public void txtSearchRoomOnAction(ActionEvent event) {
        searchRoom();
    }

    private boolean existRoom(String roomTypeID) throws Exception {
        return roomBO.roomIsExists(roomTypeID);
    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object validate = ValidationUtil.validate(map, btnAdd, btnUpdate, btnRemove);

        try {
            if (!txtRoomTypeID.getText().isEmpty()) {
                if (existRoom(txtRoomTypeID.getText())) {
                    btnAdd.setDisable(true);
                } else {
                    btnUpdate.setDisable(true);
                    btnRemove.setDisable(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (KeyCode.ENTER == keyEvent.getCode()) {
            if (validate instanceof TextField) {
                TextField textField = (TextField) validate;
                textField.requestFocus();
            } else {
                btnAdd.fire();
            }
        }
    }

    public void addRoomOnAction(ActionEvent event) {
        try {
            if (!txtRoomTypeID.getText().isEmpty() && !txtRoomType.getText().isEmpty() && !txtKeyMoneyCost.getText().isEmpty() && !txtRoomsQty.getText().isEmpty()) {
                if (!txtRoomTypeID.getText().matches("^(RM-)[0-9]{4}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid RoomType ID. (Ex :- RM-1233)").show();
                    return;
                } else if (!txtRoomType.getText().matches("^[A-z-/ ]{2,20}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Room Type").show();
                    return;
                } else if (!txtKeyMoneyCost.getText().matches("^[1-9][0-9]{0,8}([.][0-9]{2})?$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Key Money Fee.(Ex :- 2500.00)").show();
                    return;
                } else if (!txtRoomsQty.getText().matches("^[0-9]{1,4}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Rooms Quantity.(Ex :- 25)").show();
                    return;
                }

                RoomDTO dto = new RoomDTO(txtRoomTypeID.getText(), txtRoomType.getText(), txtKeyMoneyCost.getText(), Integer.parseInt(txtRoomsQty.getText()));
                boolean roomIsSaved = roomBO.saveRoom(dto);

                if (roomIsSaved) {
                    tblRoom.getItems().add(new RoomTM(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty()));
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty fields,Please fill the fields & try again..!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to add the room, try again..!").show();
        }
        clearAll();
    }

    public void updateRoomOnAction(ActionEvent event) {
        try {
            if (!tblRoom.getItems().isEmpty()) {
                if (tblRoom.getSelectionModel().getSelectedItem() != null || !txtSearchRoom.getText().isEmpty()) {
                    if (!txtRoomTypeID.getText().isEmpty() && !txtRoomType.getText().isEmpty() && !txtKeyMoneyCost.getText().isEmpty() && !txtRoomsQty.getText().isEmpty()) {
                        if (!txtRoomTypeID.getText().matches("^(RM-)[0-9]{4}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid RoomType ID. (Ex :- RM-1233)").show();
                            return;
                        } else if (!txtRoomType.getText().matches("^[A-z-/ ]{2,20}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Room Type").show();
                            return;
                        } else if (!txtKeyMoneyCost.getText().matches("^[1-9][0-9]{0,8}([.][0-9]{2})?$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Key Money Fee.(Ex :- 2500.00)").show();
                            return;
                        } else if (!txtRoomsQty.getText().matches("^[0-9]{1,4}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Rooms Quantity.(Ex :- 25)").show();
                            return;
                        }

                        boolean roomIsUpdated = roomBO.updateRoom(new RoomDTO(txtRoomTypeID.getText(), txtRoomType.getText(), txtKeyMoneyCost.getText(), Integer.parseInt(txtRoomsQty.getText())));

                        if (roomIsUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty fields,Please fill the fields & try again..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please select a room for update or enter RoomTypeID which room want to update..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Room table is empty therefore,can't update data..!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the room, Try again..!").show();
        }
        clearAll();
        loadAllRooms();
        tblRoom.refresh();
    }

    public void deleteRoomOnAction(ActionEvent event) {
        try {
            if (!tblRoom.getItems().isEmpty()) {
                if (tblRoom.getSelectionModel().getSelectedItem() != null || !txtSearchRoom.getText().isEmpty()) {
                    RoomTM tm = tblRoom.getSelectionModel().getSelectedItem();
                    String roomTypeID = txtSearchRoom.getText();

                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this room, Are you sure ?", ButtonType.YES, ButtonType.NO).showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        boolean roomIsDeleted = roomBO.deleteRoom(tm != null ? tm.getRoom_type_id() : roomTypeID);
                        if (roomIsDeleted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                        }
                    }else{
                        new Alert(Alert.AlertType.INFORMATION, "Room deletion cancelled..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please select a room for delete or enter RoomTypeID which room want to delete..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Room table is empty therefore,can't delete data..!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the room, Try again..!").show();
        }
        clearAll();
        tblRoom.refresh();
        loadAllRooms();
    }

    public void resetOnAction(ActionEvent event) {
        clearAll();
    }

    public void btnAddNewRoomOnAction(ActionEvent event) {
        clearAll();
    }

    public void searchRoomKeyReleasedOnAction(KeyEvent keyEvent) {
        if (txtSearchRoom.getText().matches("^(RM-)[0-9]{4}$")) {
            txtSearchRoom.setStyle("-fx-text-fill: BLACK");
            btnSearch.setDisable(false);
        } else {
            if (txtSearchRoom.getText().length() > 0) {
                txtSearchRoom.setStyle("-fx-text-fill: RED");
            } else {
                txtSearchRoom.setStyle("-fx-text-fill: BLACK");
            }
            btnSearch.setDisable(true);
        }
    }

    private void clearAll() {
        txtRoomTypeID.clear();
        txtRoomType.clear();
        txtRoomsQty.clear();
        txtKeyMoneyCost.clear();
        txtSearchRoom.clear();

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        setFontColor(txtRoomTypeID, txtRoomType, txtKeyMoneyCost, txtRoomsQty, txtSearchRoom);

        txtRoomTypeID.requestFocus();
        tblRoom.getSelectionModel().clearSelection();
    }
}
