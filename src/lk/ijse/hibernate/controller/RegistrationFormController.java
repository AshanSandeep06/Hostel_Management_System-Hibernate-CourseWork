package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.RegistrationBO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.util.NotificationUtil;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class RegistrationFormController {
    public Label lblTotalFee;
    public TextField txtPaidAmount;
    public Text lblRemainKeyMoneyFee;
    public JFXCheckBox cbxPaid;
    public JFXButton btnRegister;
    public TextField txtSearchStudent;
    public JFXButton btnSearchStudent;
    public ComboBox<StudentDTO> cmbStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtBirthDate;
    public JFXTextField txtGender;
    public JFXTextField txtKeyMoneyFee;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomsQty;
    public ComboBox<RoomDTO> cmbRoomTypeID;
    public TextField txtReservationID;
    public JFXDatePicker dpArrivalDate;
    public JFXDatePicker dpDepartureDate;

    // Property Injection
    private final RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATIONBO);

    public void initialize() {
        loadAllRooms();
        loadAllStudents();

        btnSearchStudent.setDisable(true);
        btnRegister.setDisable(true);

        txtReservationID.setText(generateNewReservationId());

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setStudentData(newValue);
                enableOrDisableRegisterButton();
            }
        });

        cmbRoomTypeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setRoomData(newValue);
                enableOrDisableRegisterButton();
            }
        });
    }

    private void setFontColor(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-text-fill: BLACK");
        }
    }

    private void setStudentData(StudentDTO dto) {
        cmbStudentID.setValue(dto);
        txtStudentName.setText(dto.getName());
        txtMobileNumber.setText(dto.getContact_no());
        txtAddress.setText(dto.getAddress());
        txtBirthDate.setText(dto.getDob().toString());
        txtGender.setText(dto.getGender());
    }

    private void setRoomData(RoomDTO dto) {
        cmbRoomTypeID.setValue(dto);
        txtRoomType.setText(dto.getType());
        txtKeyMoneyFee.setText(dto.getKey_money());
        txtRoomsQty.setText(String.valueOf(dto.getQty()));
        lblTotalFee.setText(dto.getKey_money());
    }

    private String generateNewReservationId() {
        try {
            return registrationBO.generateNewReservationId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadAllStudents(){
        cmbStudentID.getItems().clear();
        try{
            ArrayList<StudentDTO> allStudents = registrationBO.getAllStudents();
            for (StudentDTO studentDTO : allStudents) {
                cmbStudentID.getItems().add(studentDTO);
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllRooms(){
        cmbRoomTypeID.getItems().clear();
        try{
            ArrayList<RoomDTO> allRooms = registrationBO.getAllRooms();
            for (RoomDTO roomDTO : allRooms) {
                cmbRoomTypeID.getItems().add(roomDTO);
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void enableOrDisableRegisterButton() {
        btnRegister.setDisable(!(cmbStudentID.getSelectionModel().getSelectedItem() != null && cmbRoomTypeID.getSelectionModel().getSelectedItem() != null && !txtPaidAmount.getText().isEmpty() && dpArrivalDate.getValue()!=null && dpDepartureDate.getValue()!=null));
    }

    public void searchCustomerKeyReleasedOnAction(KeyEvent keyEvent) {
        if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
            txtSearchStudent.setStyle("-fx-text-fill: BLACK");
            btnSearchStudent.setDisable(false);
        } else {
            if (txtSearchStudent.getText().length() > 0) {
                txtSearchStudent.setStyle("-fx-text-fill: RED");
            } else {
                txtSearchStudent.setStyle("-fx-text-fill: BLACK");
            }
            btnSearchStudent.setDisable(true);
        }
    }

    private void searchStudent() {
        try {
            if (!txtSearchStudent.getText().isEmpty()) {
                if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                    String studentID = txtSearchStudent.getText();
                    StudentDTO studentDTO = registrationBO.searchStudent(studentID);
                    if (studentDTO != null) {
                        setStudentData(studentDTO);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "No students exists for this student ID..!", ButtonType.OK).show();
                        clearStudentFields();
                    }
                } else {
                    clearStudentFields();
                    new Alert(Alert.AlertType.WARNING, "Invalid student ID ..!").show();
                }
            } else {
                clearStudentFields();
                new Alert(Alert.AlertType.WARNING, "Empty field,try again..!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paidAmountOnAction(ActionEvent event) {
    }

    public void checkPaidAmountOnAction(KeyEvent keyEvent) {
        try {
            if (!txtPaidAmount.getText().isEmpty()) {
                if (txtPaidAmount.getText().matches("^[0-9]{0,8}([.][0-9]{2})?$")) {
                    if ((Double.parseDouble(txtPaidAmount.getText()) <= Double.parseDouble(lblTotalFee.getText()))) {
                        txtPaidAmount.setStyle("-fx-text-fill: BLACK");
                        enableOrDisableRegisterButton();
                    } else {
                        txtPaidAmount.setStyle("-fx-text-fill: RED");
                        btnRegister.setDisable(true);
                    }
                } else {
                    txtPaidAmount.setStyle("-fx-text-fill: RED");
                    btnRegister.setDisable(true);
                }
            } else {
                txtPaidAmount.setStyle("-fx-text-fill: BLACK");
                btnRegister.setDisable(true);
            }
        } catch (Exception e) {

        }
        calculateRemainKeyMoney();
    }

    public void checkBoxPaidOnAction(ActionEvent event) {
        txtPaidAmount.setText(lblTotalFee.getText());
        txtPaidAmount.setStyle("-fx-text-fill: BLACK");
        if(cbxPaid.isSelected()){
            txtPaidAmount.setDisable(true);
        }else{
            txtPaidAmount.setDisable(false);
        }
        enableOrDisableRegisterButton();
        calculateRemainKeyMoney();
    }

    private boolean existReservation(String reservationID) throws Exception {
        return registrationBO.reservationIsExists(reservationID);
    }

    public void btnRegisterOnAction(ActionEvent event) {
        try{
            if(!existReservation(txtReservationID.getText())){
                if(cmbStudentID.getValue()!=null && cmbRoomTypeID.getValue()!=null && !lblTotalFee.getText().isEmpty() && !txtPaidAmount.getText().isEmpty() && !lblRemainKeyMoneyFee.getText().isEmpty()){
                    if(txtPaidAmount.getText().matches("^[0-9]{0,8}([.][0-9]{2})?$")){
                        ReservationDTO reservationDTO = new ReservationDTO(
                                txtReservationID.getText(),
                                dpArrivalDate.getValue(),
                                dpDepartureDate.getValue(),
                                cmbStudentID.getValue().getStudent_id(),
                                cmbRoomTypeID.getValue().getRoom_type_id(),
                                Double.parseDouble(txtPaidAmount.getText()),
                                Double.parseDouble(lblRemainKeyMoneyFee.getText()),
                                cbxPaid.isSelected() ? "Paid" : "Non-Paid"
                        );
                        boolean reservationIsAdded = registrationBO.makeRegistration(reservationDTO);
                        if (reservationIsAdded) {
                            NotificationUtil.setNotifications("Room Registration Successful..!", "Reservation has been done successfully..!", new ImageView(new Image("lk/ijse/hibernate/asset/images/icons8-ok-100.png")),4);
                        }else{
                            new Alert(Alert.AlertType.ERROR, "Registration failed, try again..!").show();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Invalid Paid Amount, please input a valid amount..!").show();
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING, "Fill the Customer data, Room data correctly and input paid amount & try again..!").show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "This Registration is already exists..!").show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        txtReservationID.setText(generateNewReservationId());
        loadAllRooms();
        loadAllStudents();
        clearAllFields();
        enableOrDisableRegisterButton();
    }

    private void clearAllFields(){
        clearStudentFields();
        clearRoomFields();
        lblTotalFee.setText("0.00");
        lblRemainKeyMoneyFee.setText("0.00");
        txtPaidAmount.clear();
        cbxPaid.setSelected(false);
        btnRegister.setDisable(true);
        dpArrivalDate.setValue(null);
        dpDepartureDate.setValue(null);
        txtReservationID.setText(generateNewReservationId());
    }

    public void btnCancelRegisterOnAction(ActionEvent event) {
        clearAllFields();
    }

    public void arrivalDateOnAction(ActionEvent event) {
        enableOrDisableRegisterButton();
    }

    public void departureDateOnAction(ActionEvent event) {
        enableOrDisableRegisterButton();
    }

    private void calculateRemainKeyMoney(){
        try {
            if(!lblTotalFee.getText().isEmpty()){
                if(!txtPaidAmount.getText().isEmpty()){
                    double totalFee = Double.parseDouble(lblTotalFee.getText());
                    double paidAmount = Double.parseDouble(txtPaidAmount.getText());
                    double remainingKeyMoneyValue = totalFee - paidAmount;
                    lblRemainKeyMoneyFee.setText(new BigDecimal(remainingKeyMoneyValue).setScale(2).toString());
                }else{
                    lblRemainKeyMoneyFee.setText("0.00");
                }
            }else{
                lblRemainKeyMoneyFee.setText("0.00");
            }
        }catch (Exception e){
            lblRemainKeyMoneyFee.setText("0.00");
        }
    }

    public void clearStudentFieldsOnAction(ActionEvent event) {
        clearStudentFields();
    }

    public void txtSearchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void btnSearchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void clearRoomFieldsOnAction(ActionEvent event) {
        clearRoomFields();
    }

    private void clearRoomFields() {
        cmbRoomTypeID.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtRoomsQty.clear();
        txtKeyMoneyFee.clear();
        cmbRoomTypeID.requestFocus();
    }

    private void clearStudentFields() {
        txtSearchStudent.clear();
        cmbStudentID.getSelectionModel().clearSelection();
        txtStudentName.clear();
        txtMobileNumber.clear();
        txtAddress.clear();
        txtBirthDate.clear();
        txtGender.clear();
        btnSearchStudent.setDisable(true);
        setFontColor(txtSearchStudent);
        cmbStudentID.requestFocus();
    }
}
