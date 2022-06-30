package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    String student_id;
    String name;
    String address;
    String contact_no;
    LocalDate date;
    String gender;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    public Student(String student_id) {
        this.student_id = student_id;
    }

    public Student(Student student) {
        this.student_id = student.getStudent_id();

    }

    public Student(String student_id, String name, String address, String contact_no, LocalDate date, String gender) {
        this.student_id =student_id;
        this.name = name;
        this.address = address;
        this.contact_no =contact_no;
        this.date = date;
        this.gender = gender;
    }

    public Student(String student_id, String address, String contact_no, LocalDate date, String gender, String name) {
        this.student_id =student_id;
        this.address = address;
        this.contact_no =contact_no;
        this.date = date;
        this.gender = gender;
        this.name = name;
    }
}
