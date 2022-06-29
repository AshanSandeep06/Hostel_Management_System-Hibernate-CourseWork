package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.view.tdm.RoomTM;
import lk.ijse.hibernate.view.tdm.StudentTM;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class ManageStudentFormController {

    public JFXTextField txtStudentID;
    public JFXButton btnSearch;
    public JFXTextField txtStudentName;
    public JFXTextField txtMobileNumber;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colDob;
    public TableColumn colGender;
    public JFXButton btnAddNewStudent;
    public JFXTextField txtAddress;
    public JFXButton btnAdd;
    public TextField txtSearchStudent;
    public JFXDatePicker dpBirthDay;
    public JFXRadioButton rdbMale;
    public ToggleGroup btnToggle;
    public JFXRadioButton rdbFemale;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    // Property Injection
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {

    }

    public void searchStudentOnAction(ActionEvent event) {

    }

    public void resetOnAction(ActionEvent event) {
    }

    public void btnAddNewStudentOnAction(ActionEvent event) {
    }

    public void txtSearchStudentOnAction(ActionEvent event) {
    }

    public void searchStudentKeyReleasedOnAction(KeyEvent keyEvent) {
    }

    public void dpOnHidingOnAction(Event event) {
    }

    public void rdbValidateMousePressedOnAction(MouseEvent mouseEvent) {
    }

    public void addStudentOnAction(ActionEvent event) {
    }

    public void updateStudentOnAction(ActionEvent event) {

    }

    public void removeStudentOnAction(ActionEvent event) {
    }
}
