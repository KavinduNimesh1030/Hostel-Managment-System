package lk.ijse.hotelManagementSystem.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lk.ijse.hotelManagementSystem.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    String room_id;
    String type;
    String key_money;
    int qty;

    public RoomDTO(String room_id) {
        this.room_id = room_id;
    }



}
