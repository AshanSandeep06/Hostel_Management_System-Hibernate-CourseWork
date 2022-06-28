package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Room")
public class Room implements SuperEntity{
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reserveList = new ArrayList<>();

    public Room(String room_type_id, String type, String key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public Room(String room_type_id) {
        this.room_type_id = room_type_id;
    }
}
