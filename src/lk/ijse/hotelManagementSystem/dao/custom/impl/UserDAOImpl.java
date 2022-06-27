package lk.ijse.hotelManagementSystem.dao.custom.impl;

import lk.ijse.hotelManagementSystem.dao.custom.UserDAO;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.entity.User;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE User SET password = : password ,userName = : userName WHERE userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("password", entity.getPassword());
        query.setParameter("userName", entity.getUserName());
        query.setParameter("userId", entity.getUserId());
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String s) throws Exception {
        return null;
    }

    public String find(String userName, String password ) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT userId FROM User WHERE userName =:userName AND password =: password";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<String> list = query.list();
        String id = null;
        for (String s:list
             ) {
            id = s;
        }
        System.out.println("User id " +id);
        return id;

    }
    @Override
    public List<User> findAll() throws Exception {
        return null;
    }
}
