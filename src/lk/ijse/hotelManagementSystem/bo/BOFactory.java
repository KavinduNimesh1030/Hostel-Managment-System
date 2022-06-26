package lk.ijse.hotelManagementSystem.bo;

import lk.ijse.hotelManagementSystem.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hotelManagementSystem.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVE
    }
}
