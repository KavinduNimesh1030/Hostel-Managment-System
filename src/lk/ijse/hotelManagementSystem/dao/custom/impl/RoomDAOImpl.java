package lk.ijse.hotelManagementSystem.dao.custom.impl;

import lk.ijse.hotelManagementSystem.dao.custom.RoomDAO;
import lk.ijse.hotelManagementSystem.entity.Room;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Room SET type = :type,key_money = : key_money, qty = : qty  WHERE room_id = :room_id";
        Query query = session.createQuery(hql);
        query.setParameter("type", entity.getType());
        query.setParameter("key_money", entity.getKey_money());
        query.setParameter("qty", entity.getQty());
        query.setParameter("room_id", entity.getRoom_id());
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }
    public boolean update(String id ,int qty) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Room SET  qty = : qty  WHERE room_id = :room_id";
        Query query = session.createQuery(hql);
        query.setParameter("qty", qty);
        query.setParameter("room_id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = " DELETE FROM Room WHERE room_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }

    @Override
    public Room find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Room  WHERE room_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Room> RoomsList = query.list();
        for (Room r :RoomsList ) {
            return new Room(r.getRoom_id(),r.getType(),r.getKey_money(),r.getQty());
             }
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Room> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Room ";
        Query query = session.createQuery(hql);
        List<Room> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllRoomIds() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT room_id FROM Room ";
        Query query = session.createQuery(hql);
        List<String> idList = query.list();
        transaction.commit();
        session.close();
        return idList;
    }

    @Override
    public boolean addNewRoomType(String id, int qty) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Room SET qty = :qty  WHERE room_id = :room_id";
        Query query = session.createQuery(hql);
        query.setParameter("qty", qty);
        query.setParameter("room_id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }
}
