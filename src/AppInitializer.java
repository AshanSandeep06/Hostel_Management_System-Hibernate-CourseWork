/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/hibernate/view/HomePageForm.fxml"))));
        primaryStage.setTitle("Hostel Management System");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("lk/ijse/hibernate/asset/images/hostel2.jpg"));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
