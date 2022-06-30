package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Reservation")
public class Reservation implements SuperEntity{
    @Id
    private String res_id;
    private LocalDate arrival_date;
    private LocalDate departure_date;
    private double paid_key_money;
    private double remain_key_money;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;
}
