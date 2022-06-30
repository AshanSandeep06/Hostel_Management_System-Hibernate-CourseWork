package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.ViewStudentRegistrationDetailBO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.view.tdm.StudentDetailTM;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class ViewStudentRegistrationDetailFormController {

    public TableView<StudentDetailTM> tblStudentDetail;
    public TableColumn colReservationID;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colArrivalDate;
    public TableColumn colDepartureDate;
    public JFXButton btnSearch;
    public TextField txtRoomTypeID;
    public JFXTextField txtKeyMoneyCost;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomsQty;

    // Property Injection
    ViewStudentRegistrationDetailBO bo = (ViewStudentRegistrationDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VIEWSTUDENTREGISTRATIONDETAIL);

    public void initialize(){
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colArrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
        colDepartureDate.setCellValueFactory(new PropertyValueFactory<>("departure_date"));

        btnSearch.setDisable(true);
    }

    private boolean existRoom(String roomTypeID) throws Exception {
        return bo.roomIsExists(roomTypeID);
    }

    private void setRoomData(RoomDTO dto) {
        txtRoomType.setText(dto.getType());
        txtKeyMoneyCost.setText(dto.getKey_money());
        txtRoomsQty.setText(String.valueOf(dto.getQty()));
    }

    private void searchStudentDetailsByRoomTypeId() {
        try {
            if (!txtRoomTypeID.getText().isEmpty()) {
                if (txtRoomTypeID.getText().matches("^(RM-)[0-9]{4}$")) {
                    String roomTypeID = txtRoomTypeID.getText();
                    if(existRoom(roomTypeID)){
                        RoomDTO roomDTO = bo.searchRoom(roomTypeID);

                        if (roomDTO != null) {
                            setRoomData(roomDTO);
                            tblStudentDetail.getItems().clear();
                            try {
                                ArrayList<CustomDTO> list = bo.getStudentDetailsByRegisterRoomTypeId(roomTypeID);
                                for (CustomDTO dto : list) {
                                    tblStudentDetail.getItems().add(new StudentDetailTM(
                                            dto.getRes_id(),
                                            dto.getStudent_id(),
                                            dto.getName(),
                                            dto.getAddress(),
                                            dto.getContact_no(),
                                            dto.getDob(),
                                            dto.getGender(),
                                            dto.getArrival_date(),
                                            dto.getDeparture_date()
                                    ));
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "No rooms exists for this RoomTypeID..!", ButtonType.OK).show();
                        clear();
                    }

                } else {
                    clear();
                    new Alert(Alert.AlertType.WARNING, "Invalid Room Type ID ..!").show();
                }
            } else {
                clear();
                new Alert(Alert.AlertType.WARNING, "Empty field,try again..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchByRoomTypeIdOnAction(ActionEvent event) {
        searchStudentDetailsByRoomTypeId();
    }

    public void txtSearchByRoomTypeIdOnAction(ActionEvent event) {
        searchStudentDetailsByRoomTypeId();
    }

    public void keyReleasedOnAction(KeyEvent keyEvent) {
        if(txtRoomTypeID.getText().matches("^(RM-)[0-9]{4}$")){
            txtRoomTypeID.setStyle("-fx-text-fill: BLACK");
            btnSearch.setDisable(false);
        }else{
            if(txtRoomTypeID.getText().length()>0){
                txtRoomTypeID.setStyle("-fx-text-fill: RED");
            }else{
                txtRoomTypeID.setStyle("-fx-text-fill: BLACK");
            }
            btnSearch.setDisable(true);
        }
    }

    private void clear(){
        txtRoomTypeID.clear();
        txtRoomType.clear();
        txtRoomsQty.clear();
        txtKeyMoneyCost.clear();
        btnSearch.setDisable(true);
        tblStudentDetail.getItems().clear();
    }

    public void clearAllOnAction(ActionEvent event) {
        clear();
    }
}
