package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Login")
public class Login implements SuperEntity{
    private String name;
    @Id
    @Column(name = "userName")
    private String userName;
    private String password;
    private String jobRole;
}
