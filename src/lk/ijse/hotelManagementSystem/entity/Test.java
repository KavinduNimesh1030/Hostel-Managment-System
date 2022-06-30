package lk.ijse.hotelManagementSystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Test {
    @Id
    @Column
    String id;
    @Column
    String name;
}
