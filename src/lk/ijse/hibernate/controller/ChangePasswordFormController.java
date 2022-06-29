package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    public void changePasswordKeyReleasedOnAction(KeyEvent keyEvent) {
    }

    public void changeUsername_passwordOnAction(ActionEvent event) {
    }

    public void clearAllOnAction(ActionEvent event) {
    }

    public void showNewPasswordOnAction(MouseEvent mouseEvent) {
    }

    public void hideNewPasswordOnAction(MouseEvent mouseEvent) {
    }

    public void showConfirmNewPasswordOnAction(MouseEvent mouseEvent) {
    }

    public void hideConfirmNewPasswordOnAction(MouseEvent mouseEvent) {
    }
}
