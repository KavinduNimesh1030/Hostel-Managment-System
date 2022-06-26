package lk.ijse.hotelManagementSystem.dao.custom;

import lk.ijse.hotelManagementSystem.dao.CrudDAO;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;
import lk.ijse.hotelManagementSystem.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO <Room,String>{
    public List<String> getAllRoomIds() throws Exception;
    public boolean addNewRoomType(String id, int qty);
}
