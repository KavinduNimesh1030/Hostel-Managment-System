package lk.ijse.hotelManagementSystem.dao;
import lk.ijse.hotelManagementSystem.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static  DAOFactory daoFactory;
    private DAOFactory() {

    }
    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes {
        STUDENT,ROOM,RESERVE, USER
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReservationDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }

    }
}
