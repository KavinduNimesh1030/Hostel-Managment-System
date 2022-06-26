package lk.ijse.hotelManagementSystem.dao.custom;

import lk.ijse.hotelManagementSystem.dao.CrudDAO;
import lk.ijse.hotelManagementSystem.entity.Reserve;

public interface ReservationDAO extends CrudDAO<Reserve ,String> {
    public String generateNewId()throws Exception;
}
