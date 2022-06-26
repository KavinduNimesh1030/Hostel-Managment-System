package lk.ijse.hotelManagementSystem.dto;

import lk.ijse.hotelManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    String student_id;
    String name;
    String address;
    String contact_no;
    LocalDate date;
    String gender;

    public StudentDTO(String student_id) {
        this.student_id = student_id;
    }


}
