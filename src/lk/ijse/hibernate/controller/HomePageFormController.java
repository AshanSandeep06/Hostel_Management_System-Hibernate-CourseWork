package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/
public class HomePageFormController {
    public JFXProgressBar progressBar;
    public ProgressIndicator progressPercentage;
    public AnchorPane context;
    public ImageView imgHostel;

    // Property Injection
    ScaleTransition scaleTransition = new ScaleTransition();

    public void initialize() {
        cartAnimation(imgHostel);
        new screenNavigate().start();
    }

    private void cartAnimation(ImageView imgHostel) {
        scaleTransition.setNode(imgHostel);
        scaleTransition.setDuration(Duration.millis(600));
        scaleTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.setByX(0.55);
        scaleTransition.setByY(0.55);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    class screenNavigate extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 11; i++) {
                    double d = i * (0.1);
                    progressBar.setProgress(d);
                    progressPercentage.setProgress(d);

                    Thread.sleep(285);
                }

                Platform.runLater(() -> {
                    Stage stage = (Stage) context.getScene().getWindow();
                    stage.setTitle("Hostel Login");
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.show();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
