package lk.ijse.hotelManagementSystem.dao.custom;

import lk.ijse.hotelManagementSystem.dao.CrudDAO;
import lk.ijse.hotelManagementSystem.dto.CustomDTO;

public interface QueryDAO extends CrudDAO {
    public boolean deleteReservationByStudent(String id);
    public boolean deleteReservationByRoom(String id);

}
