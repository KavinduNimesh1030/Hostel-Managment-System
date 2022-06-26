package lk.ijse.hotelManagementSystem.dto;

import lk.ijse.hotelManagementSystem.entity.Room;
import lk.ijse.hotelManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReserveDTO {
    String res_id;
    LocalDate date;
    String student_id;
    String room_id;
    String status;

    public ReserveDTO(String res_id, LocalDate date, String status) {
        this.res_id =res_id;
        this.date=date;
        this.status = status;
    }


    public ReserveDTO(String res_id, LocalDate date, StudentDTO studentDTO, RoomDTO roomDTO, String status) {
        this.res_id = res_id;
        this.date = date;
        this.room_id = String.valueOf(roomDTO);
        this.student_id = String.valueOf(studentDTO);
        this.status = status;
    }

}
