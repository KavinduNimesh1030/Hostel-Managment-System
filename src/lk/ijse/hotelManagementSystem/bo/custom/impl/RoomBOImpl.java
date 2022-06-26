package lk.ijse.hotelManagementSystem.bo.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.hotelManagementSystem.bo.custom.RoomBO;
import lk.ijse.hotelManagementSystem.dao.DAOFactory;
import lk.ijse.hotelManagementSystem.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;
import lk.ijse.hotelManagementSystem.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    private final RoomDAOImpl roomDAO= (RoomDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.add(new Room(roomDTO.getRoom_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(roomDTO.getRoom_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty()));

    }

    public boolean updateRoom(String roomId,int Qty) throws Exception {
        return roomDAO.update(roomId,Qty);
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public RoomDTO searchRoom(String id) throws Exception {
        Room room = roomDAO.find(id);
        return new RoomDTO(room.getRoom_id(), room.getType(), room.getKey_money(),room.getQty());
    }

    @Override
    public List<RoomDTO> LoadAllRoom() throws Exception {
        List<RoomDTO>roomsList= FXCollections.observableArrayList();
        List<Room>list = roomDAO.findAll();
        for (Room r:list
             ) {
           roomsList.add(new RoomDTO(r.getRoom_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return roomsList;
    }

    @Override
    public List<String> getAllRoomIds() throws Exception {
            List<RoomDTO>roomIds = new ArrayList<>();
            List<String>ids = roomDAO.getAllRoomIds();

        return ids;
    }

    @Override
    public boolean AddNewRoomType(String id, int qty) throws Exception{
        return roomDAO.addNewRoomType(id,qty);
    }
}
