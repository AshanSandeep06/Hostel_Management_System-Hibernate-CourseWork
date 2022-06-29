package lk.ijse.hibernate.controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class AdminDashBoardFormController {
    public AnchorPane context;
    public Label lblRegistrationDetails;
    public Label lblChangePassword;
    public Label lblRoom;
    public Button btnRoom;
    public Button btnChangePwd;
    public Button btnRegistrationDetails;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane optionContext;
    ObservableList obList = FXCollections.observableArrayList();

    public void initialize() {
        loadDateAndTime();
        obList.addAll(lblRoom, btnRoom, lblChangePassword, btnChangePwd, lblRegistrationDetails, btnRegistrationDetails);
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date()));
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, ev -> {
            LocalTime currentTime = LocalTime.now();
            int hour = currentTime.getHour();
            String minute = currentTime.getMinute() < 10 ? "0" + currentTime.getMinute() : String.valueOf(currentTime.getMinute());
            String second = currentTime.getSecond() < 10 ? "0" + currentTime.getSecond() : String.valueOf(currentTime.getSecond());
            if (hour >= 12) {
                lblTime.setText(currentTime.getHour() + ":" + minute + ":" + second + " PM");
            } else {
                lblTime.setText(currentTime.getHour() + ":" + minute + ":" + second + " AM");
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setColors(Label label, Button btn) {
        for (Object obj : obList) {
            if (obj instanceof Label) {
                ((Label) obj).setStyle("-fx-background-color: null");
                if (((Label) obj).getId().equals(label.getId())) {
                    ((Label) obj).setStyle("-fx-background-color: #0097e6");
                }
            } else if (obj instanceof Button) {
                ((Button) obj).setStyle("-fx-background-color: null");
                if (((Button) obj).getId().equals(btn.getId())) {
                    ((Button) obj).setStyle("-fx-background-color: #0097e6");
                }
            }
        }
    }

    private void setUI(String location) throws IOException {
        optionContext.getChildren().clear();
        optionContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")));
    }

    public void manageRoomOnAction(ActionEvent event) throws IOException {
        setColors(lblRoom, btnRoom);
        setUI("ManageRoomForm");
    }

    public void changePasswordOnAction(ActionEvent event) throws IOException {
        setColors(lblChangePassword, btnChangePwd);
        setUI("ChangePasswordForm");
    }

    public void registrationDetailsOnAction(ActionEvent event) {
        setColors(lblRegistrationDetails, btnRegistrationDetails);
//        setUI("");
    }

    public void logOutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.setTitle("Hostel Login");
        stage.show();
    }
}
