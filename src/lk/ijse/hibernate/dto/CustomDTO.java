package lk.ijse.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private String name;
    private String userName;
    private String password;
    private String jobRole;

    private String res_id;
    private LocalDate arrival_date;
    private LocalDate departure_date;
    private String student_id;
    private String room_type_id;
    private double paid_key_money;
    private double remain_key_money;
    private String status;

    private String type;
    private String key_money;
    private int available_rooms_qty;
    private int unavailable_rooms_qty;
    private int qty;

    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;

    public CustomDTO(String room_type_id, String type, String key_money, int available_rooms_qty, int unavailable_rooms_qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.available_rooms_qty = available_rooms_qty;
        this.unavailable_rooms_qty = unavailable_rooms_qty;
    }
}
