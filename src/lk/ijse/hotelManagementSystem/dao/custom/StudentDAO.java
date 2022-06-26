package lk.ijse.hotelManagementSystem.dao.custom;

import lk.ijse.hotelManagementSystem.dao.CrudDAO;
import lk.ijse.hotelManagementSystem.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {
    public List<String> getAllStudentIds() throws Exception;
}
