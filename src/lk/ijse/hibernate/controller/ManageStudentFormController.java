package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.util.ValidationUtil;
import lk.ijse.hibernate.view.tdm.RoomTM;
import lk.ijse.hibernate.view.tdm.StudentTM;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
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

    public void initialize(){
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        btnSearch.setDisable(true);
        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        storeValidations();
        loadAllStudents();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, student) -> {
            if (student != null) {
                if (!tblStudent.getItems().isEmpty()) {
                    btnUpdate.setDisable(false);
                    btnRemove.setDisable(false);
                    setFontColor(txtStudentID, txtStudentName, txtMobileNumber, txtAddress, txtSearchStudent);
                    setStudentData(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact_no(),student.getDob(),student.getGender()));
                }
            }
        });
    }

    private void storeValidations() {
        map.put(txtStudentID, Pattern.compile("^(S)[0-9]{2,3}(-)[0-9]{3}$"));
        map.put(txtStudentName, Pattern.compile("^[A-z ]{3,25}$"));
        map.put(txtMobileNumber, Pattern.compile("^(0){1}[0-9]{9}$"));
        map.put(txtAddress, Pattern.compile("^[A-z0-9 ,-/]{4,45}$"));
    }

    private void setFontColor(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-text-fill: BLACK");
        }
    }

    private void setStudentData(StudentDTO dto) {
        txtStudentID.setText(dto.getStudent_id());
        txtStudentName.setText(dto.getName());
        txtMobileNumber.setText(dto.getContact_no());
        txtAddress.setText(dto.getAddress());
        dpBirthDay.setValue(dto.getDob());
        if (dto.getGender().equalsIgnoreCase("Male")) {
            rdbMale.setSelected(true);
        } else {
            rdbFemale.setSelected(true);
        }
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO s1 : allStudents) {
                tblStudent.getItems().add(new StudentTM(s1.getStudent_id(), s1.getName(), s1.getAddress(), s1.getContact_no(), s1.getDob(), s1.getGender()));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchStudentKeyReleasedOnAction(KeyEvent keyEvent) {
        if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
            txtSearchStudent.setStyle("-fx-text-fill: BLACK");
            btnSearch.setDisable(false);
        } else {
            if (txtSearchStudent.getText().length() > 0) {
                txtSearchStudent.setStyle("-fx-text-fill: RED");
            } else {
                txtSearchStudent.setStyle("-fx-text-fill: BLACK");
            }
            btnSearch.setDisable(true);
        }
    }

    private void searchStudent() {
        try {
            if (!txtSearchStudent.getText().isEmpty()) {
                if (txtSearchStudent.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                    String studentID = txtSearchStudent.getText();
                    StudentDTO studentDTO = studentBO.searchStudent(studentID);
                    if (studentDTO != null) {
                        setStudentData(studentDTO);
                        btnUpdate.setDisable(false);
                        btnRemove.setDisable(false);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "No students exists for this student ID..!", ButtonType.OK).show();
                        clearAll();
                    }
                } else {
                    clearAll();
                    new Alert(Alert.AlertType.WARNING, "Invalid student ID ..!").show();
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

    public void searchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void txtSearchStudentOnAction(ActionEvent event) {
        searchStudent();
    }

    public void resetOnAction(ActionEvent event) {
        clearAll();
    }

    public void btnAddNewStudentOnAction(ActionEvent event) {
        clearAll();
    }

    private void clearAll() {
        txtStudentID.clear();
        txtStudentName.clear();
        txtMobileNumber.clear();
        txtAddress.clear();
        txtSearchStudent.clear();
        dpBirthDay.setValue(null);
        rdbMale.setSelected(false);
        rdbFemale.setSelected(false);

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
        btnSearch.setDisable(true);

        setFontColor(txtStudentID, txtStudentName, txtMobileNumber, txtAddress, txtSearchStudent);

        txtStudentID.requestFocus();
        tblStudent.getSelectionModel().clearSelection();
    }

    private boolean existStudent(String studentID) throws Exception {
        return studentBO.studentIsExists(studentID);
    }

    private void validationForStudent() throws Exception {
        if(txtStudentID.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$") && txtStudentName.getText().matches("^[A-z ]{3,25}$") && txtMobileNumber.getText().matches("^(0){1}[0-9]{9}$") && txtAddress.getText().matches("^[A-z0-9 ,-/]{4,45}$") && dpBirthDay.getValue()!=null){
            if(rdbMale.isSelected() || rdbFemale.isSelected()){
                btnAdd.setDisable(false);
                btnUpdate.setDisable(false);
                btnRemove.setDisable(false);
            }else{
                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnRemove.setDisable(true);
            }
        }else{
            btnAdd.setDisable(true);
            btnUpdate.setDisable(true);
            btnRemove.setDisable(true);
        }
        if (existStudent(txtStudentID.getText())) {
            btnAdd.setDisable(true);
        } else {
            btnUpdate.setDisable(true);
            btnRemove.setDisable(true);
        }
    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object validate = ValidationUtil.validate(map, btnAdd, btnUpdate, btnRemove);

        try{
            if(!txtStudentID.getText().isEmpty()){
                validationForStudent();
            }

            if (KeyCode.ENTER == keyEvent.getCode()) {
                if (validate instanceof TextField) {
                    TextField textField = (TextField) validate;
                    textField.requestFocus();
                }
            }
        }catch (Exception e){

        }
    }

    public void dpOnHidingOnAction(Event event) throws Exception {
        validationForStudent();
    }

    public void rdbValidateMousePressedOnAction(MouseEvent mouseEvent) {
        try{
            if(txtStudentID.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$") && txtStudentName.getText().matches("^[A-z ]{3,25}$") && txtMobileNumber.getText().matches("^(0){1}[0-9]{9}$") && txtAddress.getText().matches("^[A-z0-9 ,-/]{4,45}$") && dpBirthDay.getValue()!=null){
                btnAdd.setDisable(false);
                btnUpdate.setDisable(false);
                btnRemove.setDisable(false);
            }else{
                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnRemove.setDisable(true);
            }
            if (existStudent(txtStudentID.getText())) {
                btnAdd.setDisable(true);
            } else {
                btnUpdate.setDisable(true);
                btnRemove.setDisable(true);
            }
        }catch (Exception e){

        }
    }

    public void addStudentOnAction(ActionEvent event) {
        try {
            if (!txtStudentID.getText().isEmpty() && !txtStudentName.getText().isEmpty() && !txtMobileNumber.getText().isEmpty() && !txtAddress.getText().isEmpty() && dpBirthDay.getValue()!=null && rdbMale.isSelected() || rdbFemale.isSelected()) {
                if (!txtStudentID.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Student ID. (Ex :- S00-001)").show();
                    return;
                } else if (!txtStudentName.getText().matches("^[A-z ]{3,25}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                    return;
                } else if (!txtMobileNumber.getText().matches("^(0){1}[0-9]{9}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Mobile Number.(Ex :- 0773461890)").show();
                    return;
                } else if (!txtAddress.getText().matches("^[A-z0-9 ,-/]{4,45}$")) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Address.(Ex :- 46/D, Makuluwa, Galle)").show();
                    return;
                }

                StudentDTO dto = new StudentDTO(txtStudentID.getText(), txtStudentName.getText(), txtAddress.getText(), txtMobileNumber.getText(), dpBirthDay.getValue(),rdbMale.isSelected() ? rdbMale.getText() : rdbFemale.getText());
                boolean studentIsSaved = studentBO.saveStudent(dto);

                if (studentIsSaved) {
                    tblStudent.getItems().add(new StudentTM(dto.getStudent_id(),dto.getName(),dto.getAddress(),dto.getContact_no(),dto.getDob(),dto.getGender()));
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty fields,Please fill the fields & try again..!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the student, try again..!").show();
        }
        clearAll();
    }

    public void updateStudentOnAction(ActionEvent event) {
        try {
            if (!tblStudent.getItems().isEmpty()) {
                if (tblStudent.getSelectionModel().getSelectedItem() != null || !txtSearchStudent.getText().isEmpty()) {
                    if (!txtStudentID.getText().isEmpty() && !txtStudentName.getText().isEmpty() && !txtMobileNumber.getText().isEmpty() && !txtAddress.getText().isEmpty() && dpBirthDay.getValue()!=null && rdbMale.isSelected() || rdbFemale.isSelected()) {
                        if (!txtStudentID.getText().matches("^(S)[0-9]{2,3}(-)[0-9]{3}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Student ID. (Ex :- S00-001)").show();
                            return;
                        } else if (!txtStudentName.getText().matches("^[A-z ]{3,25}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                            return;
                        } else if (!txtMobileNumber.getText().matches("^(0){1}[0-9]{9}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Mobile Number.(Ex :- 0773461890)").show();
                            return;
                        } else if (!txtAddress.getText().matches("^[A-z0-9 ,-/]{4,45}$")) {
                            new Alert(Alert.AlertType.ERROR, "Invalid Address.(Ex :- 46/D, Makuluwa, Galle)").show();
                            return;
                        }

                        boolean studentIsUpdated = studentBO.updateStudent(new StudentDTO(txtStudentID.getText(), txtStudentName.getText(), txtAddress.getText(), txtMobileNumber.getText(), dpBirthDay.getValue(),rdbMale.isSelected() ? rdbMale.getText() : rdbFemale.getText()));

                        if (studentIsUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty fields,Please fill the fields & try again..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please select a student for update or enter Student ID which Student want to update..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Student table is empty therefore,can't update data..!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the Student's data, Try again..!").show();
        }
        clearAll();
        loadAllStudents();
        tblStudent.refresh();
    }

    public void removeStudentOnAction(ActionEvent event) {
        try {
            if (!tblStudent.getItems().isEmpty()) {
                if (tblStudent.getSelectionModel().getSelectedItem() != null || !txtSearchStudent.getText().isEmpty()) {
                    StudentTM tm = tblStudent.getSelectionModel().getSelectedItem();
                    String studentID = txtSearchStudent.getText();

                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this Student, Are you sure ?", ButtonType.YES, ButtonType.NO).showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        boolean studentIsDeleted = studentBO.deleteStudent(tm != null ? tm.getStudent_id() : studentID);
                        if (studentIsDeleted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully", ButtonType.OK).show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Try again..!").show();
                        }
                    }else{
                        new Alert(Alert.AlertType.INFORMATION, "Student deletion cancelled..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please select a Student for delete or enter Student ID which Student want to delete..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Student table is empty therefore,can't delete data..!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Student's data, Try again..!").show();
        }
        clearAll();
        tblStudent.refresh();
        loadAllStudents();
    }
}
