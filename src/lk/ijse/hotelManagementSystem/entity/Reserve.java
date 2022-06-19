package lk.ijse.hotelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reserve")
public class Reserve {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    String res_id;
    @Column()
    LocalDate date;
    @Column()
    double Key_money;
   // @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.ALL})
    @Column(name = "Customer_id")
    Student student;
    //@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.ALL})
    @Column(name = "Room_id")
    Room room;
}
