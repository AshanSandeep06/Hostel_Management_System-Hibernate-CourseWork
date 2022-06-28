package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.LoginBO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane context;
    public ImageView imgPassword;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    private boolean checkFieldsIsEmpty() {
        return txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty();
    }

    private void storeValidations() {
        map.put(txtUserName, Pattern.compile("^[A-z0-9]{5,10}$"));
        map.put(txtPassword, Pattern.compile("^[A-z]{3,8}[0-9]{3,5}$"));
    }

    public void keyReleasedOnAction(KeyEvent keyEvent) {
        
    }

    public void loginOnAction(ActionEvent event) {

    }

    public void showPasswordOnAction(MouseEvent mouseEvent) {

    }

    public void hidePasswordOnAction(MouseEvent mouseEvent) {

    }
}
