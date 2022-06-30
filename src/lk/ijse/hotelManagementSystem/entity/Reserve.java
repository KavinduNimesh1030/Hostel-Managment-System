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
public class Reserve {
    @Id
    String res_id;
    LocalDate date;
   // @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    Student student;
    //@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    Room room;
    String status;
}
