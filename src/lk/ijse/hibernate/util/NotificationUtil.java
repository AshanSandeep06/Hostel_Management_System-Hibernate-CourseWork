package lk.ijse.hibernate.util;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class NotificationUtil {
    public static void setNotifications(String title, String text, ImageView view, int notificationTime) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(view)
                .hideAfter(Duration.seconds(notificationTime))
                .position(Pos.CENTER);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void setNotifications(String title, String text, ImageView view, int notificationTime,String s) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(view)
                .hideAfter(Duration.seconds(notificationTime))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
