package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    String userId;
    @Column()
    String userName;
    @Column()
    String password;


}
