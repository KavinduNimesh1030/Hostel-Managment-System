package lk.ijse.hotelManagementSystem.dao.custom;

import lk.ijse.hotelManagementSystem.dao.CrudDAO;
import lk.ijse.hotelManagementSystem.dto.ReserveDTO;
import lk.ijse.hotelManagementSystem.entity.Reserve;

import java.util.List;

public interface ReservationDAO extends CrudDAO<Reserve ,String> {
    public String generateNewId()throws Exception;
    public List<Object[]> LoadAllKeyMoneyDetail() throws Exception;
}
