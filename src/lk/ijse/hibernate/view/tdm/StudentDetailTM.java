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
public class StudentDetailTM {
    private String reservationId;
    private String studentId;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
    private LocalDate arrival_date;
    private LocalDate departure_date;
}
