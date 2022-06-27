package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;

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
    public TableView tblStudent;
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
}
