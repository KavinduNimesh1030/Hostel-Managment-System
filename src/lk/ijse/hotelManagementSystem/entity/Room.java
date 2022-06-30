package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    String room_id;
    String type;
    String key_money;
    int qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    public Room(String room_id) {
        this.room_id = room_id;
    }



    public Room(String room_id, String type, String key_money, int qty) {
        this.room_id =room_id;
        this.type = type;
        this.key_money =key_money;
        this.qty = qty;
    }
}
