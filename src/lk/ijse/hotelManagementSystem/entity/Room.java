package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Room")
public class Room {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    String room_id;
    @Column
    String type;
    @Column
    double monthly_Rent;
    @Column
    String qty;
}
