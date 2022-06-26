package lk.ijse.hotelManagementSystem.bo.custom.impl;

import lk.ijse.hotelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.hotelManagementSystem.dao.DAOFactory;
import lk.ijse.hotelManagementSystem.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotelManagementSystem.dto.ReserveDTO;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;
import lk.ijse.hotelManagementSystem.entity.Reserve;
import lk.ijse.hotelManagementSystem.entity.Room;
import lk.ijse.hotelManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private final ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public boolean addReservation(ReserveDTO reserveDTO) throws Exception {
        return reservationDAO.add(new Reserve(reserveDTO.getRes_id(), reserveDTO.getDate(), new Student(reserveDTO.getStudent_id()), new Room(reserveDTO.getRoom_id()), reserveDTO.getStatus()));
    }

    @Override
    public boolean updateReservation(ReserveDTO reserveDTO) throws Exception {
        return reservationDAO.update(new Reserve(reserveDTO.getRes_id(), reserveDTO.getDate(), new Student(reserveDTO.getStudent_id()), new Room(reserveDTO.getRoom_id()), reserveDTO.getStatus()));
    }

    @Override
    public boolean deleteReservation(String id) throws Exception {
        return reservationDAO.delete(id);
    }

    @Override
    public ReserveDTO searchReservation(String id) throws Exception {
        return null;
    }

    @Override
    public String generateReservationNewID() throws Exception {
        return reservationDAO.generateNewId();
    }

    @Override
    public List<ReserveDTO> LoadAllReservation() throws Exception {
        List<Reserve> list = reservationDAO.findAll();
        List<ReserveDTO> reserveList = new ArrayList<>();
        for (Reserve r : list
        ) {
            Student s1 =new Student(r.getStudent());
            Room r1 = new Room(r.getRoom());
            System.out.println("StudentId"+s1.getStudent_id());
            reserveList.add(new ReserveDTO(r.getRes_id(), r.getDate(),s1.getStudent_id() ,r1.getRoom_id(), r.getStatus()));

        }
        return reserveList;
    }
}
