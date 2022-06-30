package lk.ijse.hibernate.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

// This is for the validations
public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField, Pattern> map, JFXButton... btn){
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()) {
                addError(key, btn);
                return key;
            }
            removeError(key, btn);
        }
        return true;
    }

    private static void addError(TextField textField, JFXButton... btn) {
        if (textField.getText().length() > 0) {
            textField.setStyle("-fx-text-fill: RED");
        }else{
            textField.setStyle("-fx-text-fill: BLACK");
        }
        for (JFXButton button : btn) {
            button.setDisable(true);
        }
    }

    private static void removeError(TextField textField, JFXButton... btn) {
        textField.setStyle("-fx-text-fill: BLACK");
        for (JFXButton button : btn) {
            button.setDisable(false);
        }
    }
}