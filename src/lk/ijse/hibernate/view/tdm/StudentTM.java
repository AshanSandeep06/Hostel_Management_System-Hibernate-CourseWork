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
public class StudentTM {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
}
