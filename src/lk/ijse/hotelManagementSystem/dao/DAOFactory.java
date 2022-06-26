package lk.ijse.hotelManagementSystem.dao;
import lk.ijse.hotelManagementSystem.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static  DAOFactory daoFactory;
    private DAOFactory() {

    }
    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes {
        STUDENT,ROOM,RESERVE
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReservationDAOImpl();
            default:
                return null;
        }

    }
}
