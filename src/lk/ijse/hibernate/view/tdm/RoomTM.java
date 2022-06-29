package lk.ijse.hibernate.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTM {
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;
}
