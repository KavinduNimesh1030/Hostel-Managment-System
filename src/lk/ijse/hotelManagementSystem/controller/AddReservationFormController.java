package lk.ijse.hotelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.ReservationBO;
import lk.ijse.hotelManagementSystem.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotelManagementSystem.dto.ReserveDTO;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;
import lk.ijse.hotelManagementSystem.entity.Student;
import lk.ijse.hotelManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddReservationFormController {

    private final RoomBOImpl roomBO = (RoomBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    private final StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);
    public ComboBox<String> cmbCustomerId;
    public TextField txtKeyMoney;
    public ComboBox<String> cmbRoomId;
    public Button btnAddReservation;
    public TextField txtStatus;
    public TextField txtRoomType;
    public TextField txtQtyOnHand;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerContactNo;
    public TextField txtCustomerDob;
    public TextField txtCustomerGender;
    public TableColumn colResId;
    public TableColumn colDate;
    public TableColumn colStudentId;
    public TableColumn colRoomId;
    public TableColumn colStatus;
    public TableView<ReserveDTO> tblReservation;
    public Button btnReservationId;
    LocalDate date;
    String resId;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        colResId.setCellValueFactory(new PropertyValueFactory("res_id"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));

        Pattern statusPattern = Pattern.compile("^[A-z0-9 ,.]{2,30}$");
        map.put(txtStatus,statusPattern);

        loadAllReservation();
        loadAllStudentId();
        loadAllRoomId();
        generateNewId();
        btnReservationId.setText(generateNewId());

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          /*  txtCustomerAddress.setText(newValue.getAddress());
            txtCustomerName.setText(newValue.getName());
            txtCustomerContactNo.setText(newValue.getContact_no());
            txtCustomerDob.setText(String.valueOf(newValue.getDate()));
            txtCustomerGender.setText(newValue.getGender());*/
            LoadAllStudentDetail(newValue);
        });
        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LoadAllRoomDetail(newValue);
        });

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // cmbRoomId.setVisible(false);
            btnAddReservation.setText(newValue != null ? "Update" : "+ Add");
            if(newValue!=null) {
                cmbRoomId.setValue(newValue.getRoom_id());
                cmbCustomerId.setValue(newValue.getStudent_id());
                txtStatus.setText(newValue.getStatus());
                btnReservationId.setText(newValue.getRes_id());
                resId = newValue.getRes_id();
                date = newValue.getDate();
            }
        });

        txtKeyMoney.setEditable(false);
        txtQtyOnHand.setEditable(false);
        txtRoomType.setEditable(false);
        txtCustomerGender.setEditable(false);
        txtCustomerDob.setEditable(false);
        txtCustomerName.setEditable(false);
        txtCustomerContactNo.setEditable(false);
        txtCustomerAddress.setEditable(false);

    }

    private void loadAllReservation() {
        try {
            List<ReserveDTO> list =reservationBO.LoadAllReservation();
            ObservableList<ReserveDTO> obList = FXCollections.observableArrayList();
            for (ReserveDTO r:list
                 ) {
                obList.add(new ReserveDTO(r.getRes_id(),r.getDate(),r.getStudent_id(),r.getRoom_id(),r.getStatus()));
                tblReservation.setItems(obList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllRoomId() {
        try {
            cmbRoomId.setItems(FXCollections.observableArrayList(roomBO.getAllRoomIds()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LoadAllRoomDetail(String selectedId) {
        try {
            RoomDTO roomDTO = roomBO.searchRoom(selectedId);
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(roomDTO.getKey_money());
            txtQtyOnHand.setText(String.valueOf(roomDTO.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LoadAllStudentDetail(String selectedId) {
        try {
            StudentDTO studentDTO = studentBO.searchStudent(selectedId);
            txtCustomerAddress.setText(studentDTO.getAddress());
            txtCustomerName.setText(studentDTO.getName());
            txtCustomerContactNo.setText(studentDTO.getContact_no());
            txtCustomerDob.setText(String.valueOf(studentDTO.getDate()));
            txtCustomerGender.setText(studentDTO.getGender());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentId()  {
        try {
            cmbCustomerId.setItems(FXCollections.observableArrayList(studentBO.getAllStudentIds()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String generateNewId(){
        try {
           return  reservationBO.generateReservationNewID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddReservationOnAction(ActionEvent actionEvent) {
        if(btnAddReservation.getText().equals("+ Add")) {
            String resId = generateNewId();
            try {
                reservationBO.addReservation(new ReserveDTO(resId, LocalDate.now(), cmbCustomerId.getValue(), cmbRoomId.getValue(), txtStatus.getText()));
                int qty = Integer.parseInt(txtQtyOnHand.getText());
                boolean b = roomBO.updateRoom(cmbRoomId.getValue(), (qty - 1));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation Done!!").show();
                    loadAllReservation();
                    clearText();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }else {
            try {
                reservationBO.updateReservation(new ReserveDTO(resId,date,cmbCustomerId.getValue(), cmbRoomId.getValue(), txtStatus.getText()));
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation Done!!").show();
                loadAllReservation();
                clearText();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
            }
        }
    }
    public void clearText(){
        txtKeyMoney.clear();
//        cmbCustomerId.setValue(null);
//        cmbRoomId.setValue(null);
        cmbRoomId.setPromptText("Room Id");
        cmbCustomerId.setPromptText("Student Id");
        txtRoomType.clear();
        txtStatus.clear();
        txtQtyOnHand.clear();
        txtCustomerAddress.clear();
        txtCustomerDob.clear();
        txtCustomerGender.clear();
        txtCustomerName.clear();
        txtCustomerContactNo.clear();

    }

    public void btnDeleteReservationOnAction(ActionEvent actionEvent) {
        try {
            reservationBO.deleteReservation(resId);
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted!!").show();
            loadAllReservation();
            clearText();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.CONFIRMATION, "Something Wrong!").show();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddReservation);
        if (keyEvent.getCode() == KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAddReservation);

            if (response instanceof TextField ) {
                TextField txtField =(TextField) response;
                txtField.requestFocus();
            }else if(response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
