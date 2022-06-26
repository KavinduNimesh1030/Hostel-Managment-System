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
    String key_money;
    @Column
    int qty;

    public Room(String room_id) {
        this.room_id = room_id;
    }

    public Room(Room room) {
        this.room_id =room.getRoom_id();
    }
}
