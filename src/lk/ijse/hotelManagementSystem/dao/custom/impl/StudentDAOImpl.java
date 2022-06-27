package lk.ijse.hotelManagementSystem.dao.custom.impl;

import lk.ijse.hotelManagementSystem.dao.SuperDAO;
import lk.ijse.hotelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Student SET name = :student_name , address = :address, contact_no = :contact_no, date = :date,gender = :gender WHERE student_id = :student_id";
        Query query = session.createQuery(hql);
        query.setParameter("student_name", entity.getName());
        query.setParameter("address", entity.getAddress());
        query.setParameter("contact_no", entity.getContact_no());
        query.setParameter("date", entity.getDate());
        query.setParameter("gender", entity.getGender());
        query.setParameter("student_id" ,entity.getStudent_id());
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
        String hql = " DELETE FROM Student WHERE student_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount > 0 ? true : false;
    }

    @Override
    public Student find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String id = s;
        String hql = "FROM Student  WHERE student_id = :search_id";
        Query query = session.createQuery(hql);
        query.setParameter("search_id", id);
        List<Student> studentsList = query.list();
        for (Student student : studentsList) {
            return new Student(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),student.getDate(),student.getGender());
        }
        transaction.commit();
        session.close();
        return null;

    }

    @Override
    public List<Student> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT student_id FROM Student ";
        Query query = session.createQuery(hql);
        List<String> idList = query.list();
        transaction.commit();
        session.close();
        return idList;
    }
//    public void a (){
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        String hql2 = "SELECT student_id FROM Student WHERE name =:name AND address =: address";
//    }
//
}
