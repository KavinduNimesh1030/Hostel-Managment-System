package lk.ijse.hotelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.hotelManagementSystem.dto.CustomDTO;

import java.time.LocalDate;
import java.util.List;

public class ManageKeyMoneyFormController {
    public TableView<CustomDTO> tblReservation;
    public TableColumn colResId;
    public TableColumn colDate;
    public TableColumn colStudentId;
    public TableColumn colRoomId;
    public TableColumn colStatus;
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);
    public TableColumn colStudent_id;
    public TableColumn colStudentName;
    public TableColumn colNumber;


    public void initialize(){
        colStudentId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory("contact_no"));
        colResId.setCellValueFactory(new PropertyValueFactory("res_id"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));


        loadAll();
    }

    private void loadAll() {
        try {
            List<Object[]> list = reservationBO.LoadAllKeyMoneyDetail();
            ObservableList<CustomDTO> obList = FXCollections.observableArrayList();
            for (Object [] r:list
            ) {
                System.out.println(r[0].toString());
                System.out.println(r[1].toString());
                System.out.println(r[2].toString());
                System.out.println(r[3].toString());
                System.out.println(r[4].toString());
                System.out.println(r[5].toString());
                System.out.println(r[6].toString());

                obList.add(new CustomDTO(r[0].toString(),r[1].toString(),r[3].toString(),r[4].toString(),LocalDate.parse(r[5].toString()),r[6].toString(),r[7].toString()));

                tblReservation.setItems(obList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
