package lk.ijse.hotelManagementSystem.bo.custom;

import lk.ijse.hotelManagementSystem.bo.SuperBO;
import lk.ijse.hotelManagementSystem.dto.ReserveDTO;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    public boolean addReservation(ReserveDTO reserveDTO) throws Exception;

    public boolean updateReservation(ReserveDTO reserveDTO) throws Exception;

    public boolean deleteReservation(String id) throws Exception;

    public ReserveDTO searchReservation(String id) throws Exception;

    public String generateReservationNewID()throws Exception;

    public List<ReserveDTO> LoadAllReservation() throws Exception;

    public List<Object[]> LoadAllKeyMoneyDetail() throws Exception;
}
