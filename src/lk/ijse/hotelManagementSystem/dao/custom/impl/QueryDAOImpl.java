package lk.ijse.hotelManagementSystem.dao.custom.impl;

import lk.ijse.hotelManagementSystem.dao.custom.QueryDAO;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public boolean add(Object dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(Object dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public Object find(Object o) throws Exception {
        return null;
    }

    @Override
    public List findAll() throws Exception {
        return null;
    }

    @Override
    public boolean deleteReservationByStudent(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Reserve WHERE student.student_id =: id");
        query.setParameter("id",id);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReservationByRoom(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Reserve WHERE room.room_id =: id");
        query.setParameter("id",id);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        if(i > 0){
            return true;
        }else {
            return false;
        }

    }
}
