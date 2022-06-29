package lk.ijse.hibernate.util;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class PasswordUtil {
    public static void showPassword(ImageView image, TextField textField){
        Image img = new Image("lk/ijse/hibernate/asset/images/show.png");
        image.setImage(img);

        textField.setPromptText(textField.getText());
        textField.setText("");
        textField.setDisable(true);
        textField.requestFocus();
    }

    public static void hidePassword(ImageView image, TextField textField){
        Image img = new Image("lk/ijse/hibernate/asset/images/hide.png");
        image.setImage(img);

        textField.setText(textField.getPromptText());
        textField.setPromptText("");
        textField.setDisable(false);
    }
}
