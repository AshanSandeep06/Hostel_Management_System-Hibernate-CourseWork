package lk.ijse.hibernate.dto;

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
public class ReservationDTO {
    private String res_id;
    private LocalDate arrival_date;
    private LocalDate departure_date;
    private String student_id;
    private String room_type_id;
    private double paid_key_money;
    private double remain_key_money;
    private String status;
}
