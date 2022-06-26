package lk.ijse.hotelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;

import java.time.LocalDate;
import java.util.List;

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

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory("date"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            txtCustomerId.setText(newValue.getStudent_id());
            txtCustomerName.setText(newValue.getName());
            txtCustomerAddress.setText(newValue.getAddress());
            txtCustomerContactNo.setText(newValue.getContact_no());
            txtCustomerGender.setText(newValue.getGender());
            txtCustomerDob.setText(String.valueOf(newValue.getDate()));
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
}
