package lk.ijse.hotelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;
import lk.ijse.hotelManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddStudentFormController {
    private final StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public Button btnDelete;
    public Button btnSave;
    public TextField txtCustomerContactNo;
    public TextField txtCustomerDob;
    public TextField txtCustomerGender;
    public TextField txtCustomerSearch;
    public TableView<StudentDTO> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDob;
    public TableColumn colGender;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory("date"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));

        Pattern idPattern = Pattern.compile("^(S)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{2,30}$");
        Pattern teleNoPattern = Pattern.compile("^[\\+](94)[0-9]{9}$");
        Pattern DobPattern = Pattern.compile("[0-9 ,-]{1,13}");
        Pattern genderPattern = Pattern.compile("^[A-z ]{3,20}$");

        map.put(txtCustomerId,idPattern);
        map.put(txtCustomerName,namePattern);
        map.put(txtCustomerAddress,addressPattern);
        map.put(txtCustomerContactNo,teleNoPattern);
        map.put(txtCustomerDob,DobPattern);
        map.put(txtCustomerGender,genderPattern);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            if(newValue != null) {
                txtCustomerId.setText(newValue.getStudent_id());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());
                txtCustomerContactNo.setText(newValue.getContact_no());
                txtCustomerGender.setText(newValue.getGender());
                txtCustomerDob.setText(String.valueOf(newValue.getDate()));
            }
        });
        try {
            loadAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudent() throws Exception {
        List<StudentDTO> list = studentBO.LoadAllStudent();
        ObservableList<StudentDTO> obList = FXCollections.observableArrayList();
        for (StudentDTO s : list
        ) {
            obList.add(new StudentDTO(s.getStudent_id(), s.getName(), s.getAddress(), s.getContact_no(), s.getDate(), s.getGender()
            ));

        }

        tblCustomer.setItems(obList);
    }

    public void btnDeleteCustomer(ActionEvent actionEvent) {
        try {
            boolean b = studentBO.deleteReservation(txtCustomerId.getText());

                studentBO.deleteStudent(txtCustomerId.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!!").show();
                loadAllStudent();
                clearText();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveCustomer(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save")) {
            try {
                studentBO.addStudent(new StudentDTO(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerContactNo.getText(), LocalDate.parse(txtCustomerDob.getText()), txtCustomerGender.getText()));
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
                loadAllStudent();
                tblCustomer.refresh();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            try {
                studentBO.updateStudent(new StudentDTO(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerContactNo.getText(), LocalDate.parse(txtCustomerDob.getText()), txtCustomerGender.getText()));
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
                tblCustomer.refresh();
                loadAllStudent();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

    }

    public void btnCustomerSearchOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO studentDTO = studentBO.searchStudent(txtCustomerSearch.getText());
            txtCustomerId.setText(txtCustomerSearch.getText());
            txtCustomerName.setText(studentDTO.getName());
            txtCustomerAddress.setText(studentDTO.getAddress());
            txtCustomerContactNo.setText(studentDTO.getContact_no());
            txtCustomerDob.setText(String.valueOf(studentDTO.getDate()));
            txtCustomerGender.setText(studentDTO.getGender());
            btnSave.setText("Update");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Empty result").show();
        }
    }

    public void clearText() {
        txtCustomerId.clear();
        txtCustomerAddress.clear();
        txtCustomerDob.clear();
        txtCustomerGender.clear();
        txtCustomerName.clear();
        txtCustomerContactNo.clear();
        txtCustomerSearch.clear();
    }

    public void btnAddNewCustomer(ActionEvent actionEvent) {
        btnSave.setText("Save");
        clearText();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnSave);

            if (response instanceof TextField ) {
                TextField txtField =(TextField) response;
                txtField.requestFocus();
            }else if(response instanceof Boolean) {
                System.out.println("Work");
            }
        }

    }
}
