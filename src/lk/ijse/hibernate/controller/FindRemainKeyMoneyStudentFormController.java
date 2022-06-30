package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.FindRemainKeyMoneyStudentsBO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.view.tdm.RemainKeyMoneyStudentsTM;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class FindRemainKeyMoneyStudentFormController {
    public TableView<RemainKeyMoneyStudentsTM> tblRemainKeyMoneyStudents;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colDepartureDate;
    public TableColumn colReservationID;
    public TableColumn colRoomTypeID;
    public TableColumn colRemainKeyMoneyFee;
    public TableColumn colArrivalDate;
    public TableColumn colPaidKeyMoney;
    public JFXButton btnSearch;
    public TextField txtSearchStudent;
    private String id;

    private final FindRemainKeyMoneyStudentsBO bo = (FindRemainKeyMoneyStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FINDREMAINKEYMONEYSTUDENTSBO);

    public void initialize() {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDepartureDate.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRemainKeyMoneyFee.setCellValueFactory(new PropertyValueFactory<>("remain_keyMoney_fee"));
        colArrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
        colPaidKeyMoney.setCellValueFactory(new PropertyValueFactory<>("paid_keyMoney_fee"));

        loadAllRemainKeyMoneyStudentsDetails();
        btnSearch.setDisable(true);
    }

    private void loadAllRemainKeyMoneyStudentsDetails() {
        tblRemainKeyMoneyStudents.getItems().clear();
        try {
            ArrayList<CustomDTO> list = bo.loadAllRemainKeyMoneyStudentDetails();
            for (CustomDTO dto : list) {
                tblRemainKeyMoneyStudents.getItems().add(new RemainKeyMoneyStudentsTM(
                        dto.getStudent_id(),
                        dto.getName(),
                        dto.getRes_id(),
                        dto.getRoom_type_id(),
                        dto.getPaid_key_money(),
                        dto.getRemain_key_money(),
                        dto.getArrival_date(),
                        dto.getDeparture_date()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchStudentKeyReleasedOnAction(KeyEvent keyEvent) {
        try{
            if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                txtSearchStudent.setStyle("-fx-text-fill: BLACK");
                btnSearch.setDisable(false);
                if(!id.equals(txtSearchStudent.getText())){
                    loadAllRemainKeyMoneyStudentsDetails();
                }
            } else {
                if (txtSearchStudent.getText().length() > 0) {
                    txtSearchStudent.setStyle("-fx-text-fill: RED");
                } else {
                    txtSearchStudent.setStyle("-fx-text-fill: BLACK");
                }
                btnSearch.setDisable(true);
                loadAllRemainKeyMoneyStudentsDetails();
            }
        }catch (Exception e){

        }
    }

    public void searchStudentOnAction(ActionEvent event) {
        try {
            if (!txtSearchStudent.getText().isEmpty()) {
                if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                    String studentID = txtSearchStudent.getText();
                    id = studentID;
                    StudentDTO studentDTO = bo.searchStudent(studentID);
                    if (studentDTO != null) {
                        tblRemainKeyMoneyStudents.getItems().clear();
                        ArrayList<CustomDTO> remainKeyMoneyDetails = bo.findRemainKeyMoneyDetails(studentID);
                        for (CustomDTO dto : remainKeyMoneyDetails) {
                            tblRemainKeyMoneyStudents.getItems().add(new RemainKeyMoneyStudentsTM(
                                    dto.getStudent_id(),
                                    dto.getName(),
                                    dto.getRes_id(),
                                    dto.getRoom_type_id(),
                                    dto.getPaid_key_money(),
                                    dto.getRemain_key_money(),
                                    dto.getArrival_date(),
                                    dto.getDeparture_date()
                            ));
                        }
                    } else {
                        loadAllRemainKeyMoneyStudentsDetails();
                        new Alert(Alert.AlertType.ERROR, "No students exists for this student ID..!", ButtonType.OK).show();

                    }
                } else {
                    loadAllRemainKeyMoneyStudentsDetails();
                    new Alert(Alert.AlertType.WARNING, "Invalid student ID ..!").show();
                }
            } else {
                loadAllRemainKeyMoneyStudentsDetails();
                new Alert(Alert.AlertType.WARNING, "Empty field,try again..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
