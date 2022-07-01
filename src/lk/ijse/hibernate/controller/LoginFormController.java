package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.LoginBO;
import lk.ijse.hibernate.dto.LoginDTO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import lk.ijse.hibernate.util.NotificationUtil;
import lk.ijse.hibernate.util.PasswordUtil;
import lk.ijse.hibernate.util.ValidationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
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
    public static LoginDTO user;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    //Property Injection
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void initialize() {
        try {
            if(loginBO.existLogins().size() == 0){
                Session session = FactoryConfiguration.getInstance().getSession();
                Transaction transaction = session.beginTransaction();

                loginBO.saveLoginAccess(new LoginDTO("Kamal Silva","admin065","admin123","Admin"));
                loginBO.saveLoginAccess(new LoginDTO("Himaya Ruwini","himaya099","himaya2001","Receptionist"));
                loginBO.saveLoginAccess(new LoginDTO("Amali Tharaka","amali123","amali1998","Receptionist"));

                transaction.commit();
                session.close();
            }

            txtUserName.requestFocus();
            btnLogin.setDisable(true);
            storeValidations();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkFieldsIsEmpty() {
        return txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty();
    }

    private void storeValidations() {
        map.put(txtUserName, Pattern.compile("^[A-z0-9]{5,10}$"));
        map.put(txtPassword, Pattern.compile("^[A-z]{3,8}[0-9]{3,5}$"));
    }

    public void keyReleasedOnAction(KeyEvent keyEvent) {
        Object validate = ValidationUtil.validate(map, btnLogin);

        if (KeyCode.ENTER == keyEvent.getCode()) {
            if (validate instanceof TextField) {
                TextField textField = (TextField) validate;
                textField.requestFocus();
            } else {
                btnLogin.fire();
            }
        }
    }

    private void setUI(String location, String title) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
    }

    public void loginOnAction(ActionEvent event) {
        try {
            if (checkFieldsIsEmpty()) {
                NotificationUtil.setNotifications("Login Failed..!", "Please enter the username & password..!", new ImageView(new Image("lk/ijse/hibernate/asset/images/icons8-help-100.png")), 3);
            } else {
                LoginDTO loginDTO = loginBO.getLoginAccess(txtUserName.getText(), txtPassword.getText());
                user = loginDTO;

                if (loginDTO != null) {
                    if (loginDTO.getJobRole().equals("Admin")) {
                        // Redirect to Admin DashBoard
                        setUI("AdminDashBoardForm","Admin DashBoard");
                        NotificationUtil.setNotifications("Login Successfully..!", "You have Successfully login to the System..!", new ImageView(new Image("lk/ijse/hibernate/asset/images/icons8-ok-100.png")), 4, "");
                    } else {
                        // Redirect to Receptionist DashBoard
                        setUI("ReceptionistDashBoardForm","Receptionist DashBoard");
                        NotificationUtil.setNotifications("Login Successfully..!", "You have Successfully login to the System..!", new ImageView(new Image("lk/ijse/hibernate/asset/images/icons8-ok-100.png")), 4, "");
                    }

                } else {
                    NotificationUtil.setNotifications("Login Failed..!", "username or password is incorrect..!", new ImageView(new Image("lk/ijse/hibernate/asset/images/Invalid Password.png")), 3);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.showPassword(imgPassword, txtPassword);
    }

    public void hidePasswordOnAction(MouseEvent mouseEvent) {
        PasswordUtil.hidePassword(imgPassword, txtPassword);
    }
}
