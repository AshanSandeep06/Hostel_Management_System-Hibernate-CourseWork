package lk.ijse.hibernate.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RemainKeyMoneyStudentsTM {
    private String student_id;
    private String name;
    private String reservation_id;
    private String room_type_id;
    private double paid_keyMoney_fee;
    private double remain_keyMoney_fee;
    private LocalDate arrival_date;
    private LocalDate departure_date;
}
