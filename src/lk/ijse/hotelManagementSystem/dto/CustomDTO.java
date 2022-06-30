package lk.ijse.hotelManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDTO {
    String student_id;
    String name;
    String contact_no;
    String res_id;
    LocalDate date;
    String room_id;
    String status;
}
