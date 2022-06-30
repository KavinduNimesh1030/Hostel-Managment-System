package lk.ijse.hotelManagementSystem.dao.custom.impl;

import lk.ijse.hotelManagementSystem.dao.custom.ReservationDAO;
import lk.ijse.hotelManagementSystem.entity.Reserve;
import lk.ijse.hotelManagementSystem.entity.Room;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Reserve SET status = : status WHERE res_id = :res_id";
        Query query = session.createQuery(hql);

        query.setParameter("status", entity.getStatus());
        query.setParameter("res_id", entity.getRes_id());


        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = " DELETE FROM Reserve WHERE res_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", s);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0;
    }

    @Override
    public Reserve find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Reserve> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Reserve ";
        Query query = session.createQuery(hql);
        List<Reserve> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNewId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT res_id FROM Reserve ORDER BY res_id DESC";
        Query query = session.createQuery(hql);
        List<String> idList = query.list();
        String id = null;

        for (String ids : idList
        ) {
            id = ids;
            System.out.println("IDG" + id);
            break;
        }

        transaction.commit();
        session.close();

        String newVersion;
        if (id.equals(null)) {
            return "RE001";

        } else {
            int i = (Integer.parseInt(id.substring(2)) + 1);

            if (i >= 10) {
                newVersion = "RE0" + i;
            } else {
                newVersion = "RE00" + i;
            }
            System.out.println(newVersion);
            return newVersion;
        }


    }

    @Override
    public List<Object[]> LoadAllKeyMoneyDetail() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT s.student_id, s.name, s.address,  s.contact_no, res.res_id,res.date, res.room.room_id, res.status FROM Reserve res INNER JOIN Student s ON res.student.student_id = s.student_id WHERE res.status!=:paid";
        Query query = session.createQuery(hql);
        query.setParameter("paid","paid");
        List<Object[]> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
