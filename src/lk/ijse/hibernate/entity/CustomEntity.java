package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomEntity {
    /*private String name;
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
    private String gender;*/

    /*public CustomEntity(String room_type_id, String type, String key_money, int available_rooms_qty, int unavailable_rooms_qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.available_rooms_qty = available_rooms_qty;
        this.unavailable_rooms_qty = unavailable_rooms_qty;
    }

    public CustomEntity(String student_id, String name, String res_id, double remain_key_money, LocalDate arrival_date, LocalDate departure_date, String status) {
        this.name = name;
        this.res_id = res_id;
        this.arrival_date = arrival_date;
        this.departure_date = departure_date;
        this.student_id = student_id;
        this.remain_key_money = remain_key_money;
        this.status = status;
    }*/

    private String userName;
    private String password;
    private String jobRole;

    private String res_id;
    private LocalDate arrival_date;
    private LocalDate departure_date;
    private double paid_key_money;
    private double remain_key_money;
    private String status;
    private Student student;
    private Room room;

    private String room_type_id;
    private String type;
    private String key_money;
    private int available_rooms_qty;
    private int unavailable_rooms_qty;
    private int qty;
    private List<Reservation> reserveList = new ArrayList<>();

    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
}
