package lk.ijse.hotelManagementSystem.bo.custom;

import lk.ijse.hotelManagementSystem.bo.SuperBO;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean addStudent(StudentDTO studentDTO) throws Exception;

    public boolean updateStudent(StudentDTO studentDTO) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public StudentDTO searchStudent(String id) throws Exception;

    public List<StudentDTO> LoadAllStudent() throws Exception;

    public List<String> getAllStudentIds() throws Exception;
}
