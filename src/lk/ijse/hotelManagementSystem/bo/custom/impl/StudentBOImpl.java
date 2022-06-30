package lk.ijse.hotelManagementSystem.bo.custom.impl;

import lk.ijse.hotelManagementSystem.bo.custom.StudentBO;
import lk.ijse.hotelManagementSystem.dao.DAOFactory;
import lk.ijse.hotelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;
import lk.ijse.hotelManagementSystem.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getStudent_id(),studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDate(),studentDTO.getGender(),studentDTO.getName()));

    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws Exception {
        Student student =studentDAO.find(id);
        return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),student.getDate(),student.getGender());
    }

    @Override
    public List<StudentDTO> LoadAllStudent() throws Exception {
        List<StudentDTO>studentDTOList =new ArrayList<>();
        List<Student>list = studentDAO.findAll();
        for (Student s:list
             ) {
             studentDTOList.add(new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDate(),s.getGender()));

        }
        return studentDTOList;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<String> list = studentDAO.getAllStudentIds();
        return list;
    }
}
