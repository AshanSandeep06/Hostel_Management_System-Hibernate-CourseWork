package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.ChangePasswordBO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.util.PasswordUtil;
import lk.ijse.hibernate.util.ValidationUtil;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class ChangePasswordFormController {
    public JFXTextField txtJobRole;
    public JFXTextField txtCurrentPwd;
    public TextField txtNewPwd;
    public TextField txtConfirmNewPwd;
    public JFXTextField txtName;
    public JFXTextField txtCurrentUserName;
    public TextField txtNewUserName;
    public Label lblConfirmNewPwd;
    public JFXButton btnChangeUsername_pwd;
    public ImageView imgPassword;
    public ImageView imgPassword1;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    //Property Injection
    private final ChangePasswordBO changePasswordBO = (ChangePasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHANGEPASSWORDBO);

    public void initialize() {
        btnChangeUsername_pwd.setDisable(true);
        storeValidations();
        loadUserDetails(LoginFormController.user);
    }

    public void changePasswordKeyReleasedOnAction(KeyEvent keyEvent) {
        Object validate = ValidationUtil.validate(map, btnChangeUsername_pwd);

        if (txtNewPwd.getText().length() > 0) {
            if (txtConfirmNewPwd.getText().length() > 0) {
                if (txtNewPwd.getText().equals(txtConfirmNewPwd.getText())) {
                    lblConfirmNewPwd.setStyle("-fx-text-fill: GREEN");
                    lblConfirmNewPwd.setText("✔ Password is right");
                } else {
                    lblConfirmNewPwd.setStyle("-fx-text-fill: RED");
                    lblConfirmNewPwd.setText("*Password doesn't match");
                }
            }else{
                lblConfirmNewPwd.setText("");
            }
        }

        if (KeyCode.ENTER == keyEvent.getCode()) {
            if (validate instanceof TextField) {
                TextField textField = (TextField) validate;
                textField.requestFocus();
            }
        }
    }

    private void loadUserDetails(LoginDTO loginDTO) {
        txtName.setText(loginDTO.getName());
        txtJobRole.setText(loginDTO.getJobRole());
        txtCurrentUserName.setText(loginDTO.getUserName());
        txtCurrentPwd.setText(loginDTO.getPassword());
    }

    private void storeValidations() {
        map.put(txtNewUserName, Pattern.compile("^[A-z0-9]{5,10}$"));
        map.put(txtNewPwd, Pattern.compile("^[A-z]{3,8}[0-9]{3,5}$"));
        map.put(txtConfirmNewPwd, Pattern.compile("^[A-z]{3,8}[0-9]{3,5}$"));
    }

    private void clear() {
        txtNewPwd.clear();
        txtConfirmNewPwd.clear();
        txtNewUserName.clear();
        lblConfirmNewPwd.setText("");
    }

    public void clearAllOnAction(ActionEvent event) {
        clear();
    }

    public void showNewPasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.showPassword(imgPassword, txtNewPwd);
    }

    public void hideNewPasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.hidePassword(imgPassword, txtNewPwd);
    }

    public void showConfirmNewPasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.showPassword(imgPassword1, txtConfirmNewPwd);
    }

    public void hideConfirmNewPasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.hidePassword(imgPassword1, txtConfirmNewPwd);
    }

    public void changeUsername_passwordOnAction(ActionEvent event) {
        try {
            if (!txtName.getText().isEmpty() && !txtJobRole.getText().isEmpty() && !txtCurrentPwd.getText().isEmpty() && !txtCurrentUserName.getText().isEmpty() && !txtNewUserName.getText().isEmpty() && !txtNewPwd.getText().isEmpty() && !txtConfirmNewPwd.getText().isEmpty()) {
                if (txtNewUserName.getText().matches("^[A-z0-9]{5,10}$") && txtNewPwd.getText().matches("^[A-z]{3,8}[0-9]{3,5}$") && txtConfirmNewPwd.getText().matches("^[A-z]{3,8}[0-9]{3,5}$")) {
                    if (txtNewPwd.getText().equals(txtConfirmNewPwd.getText())) {
                        String currentUserName = txtCurrentUserName.getText();
                        boolean isChanged = changePasswordBO.changeUsernameAndPassword(currentUserName, txtNewUserName.getText(), txtConfirmNewPwd.getText());
                        LoginDTO loginDTO = changePasswordBO.getLoginAccess(txtNewUserName.getText(),txtConfirmNewPwd.getText());
                        loadUserDetails(loginDTO);
                        LoginFormController.user = loginDTO;
                        if (isChanged) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Username and Password changed successfully..!", ButtonType.OK).show();
                            clear();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Something went wrong, try again.!", ButtonType.OK).show();
                            clear();
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Your Confirmation Password doesn't match, Please try again..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Username or Password, Please enter valid data..!", ButtonType.OK).show();
                    clear();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty fields, try again..!", ButtonType.CLOSE).show();
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
