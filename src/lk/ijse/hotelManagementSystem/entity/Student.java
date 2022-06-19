package lk.ijse.hotelManagementSystem.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Student")
public class Student {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    String student_id;
    @Column()
    String name;
    @Column()
    String address;
    @Column()
    String contact_no;
    @Column()
    LocalDate date;
    @Column()
    String gender;
}
