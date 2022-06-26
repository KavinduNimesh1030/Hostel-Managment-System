package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reservevation")
public class Reserve {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    String res_id;
    @Column()
    LocalDate date;
   // @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.ALL})
    @Column(name = "student_id")
    Student student;
    //@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.ALL})
    @Column(name = "room_id")
    Room room;
    @Column
    String status;
}
