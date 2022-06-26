package lk.ijse.hotelManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.RoomBOImpl;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotelManagementSystem.dto.RoomDTO;
import lk.ijse.hotelManagementSystem.entity.Room;

import java.util.List;

public class AddRoomFormController {
    private final RoomBOImpl roomBO = (RoomBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public TextField txtRoomId;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public TextField txtRoomSearch;
    public Button btnDelete;
    public Button btnSave;
    public ComboBox<String>cmbRoomId;
    public TextField txtQtyOnHand;
    public TableView<RoomDTO> tblRoomType;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQtyOnHand;

    public  void initialize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory("key_money"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qty"));
        loadAllRoom();
        setRoomTypeIds();
        txtRoomId.setVisible(false);
        setEditable(false);

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setRoomDetails(newValue);
        });
        tblRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           // cmbRoomId.setVisible(false);
            btnSave.setText(newValue != null ? "Update" : "Save");
            txtQty.setDisable(newValue != null ? true : false);
            setEditable(true);
            txtRoomId.setEditable(false);
            assert newValue != null;
            if(newValue!=null) {
                cmbRoomId.setValue(newValue.getRoom_id());
                txtRoomId.setText(newValue.getRoom_id());
                txtRoomType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKey_money());
                txtQtyOnHand.setText(String.valueOf(newValue.getQty()));
            }


        });


    }

    private void loadAllRoom() {
        try {
           List<RoomDTO> list = roomBO.LoadAllRoom();
            ObservableList<RoomDTO>obList = FXCollections.observableArrayList();
            for (RoomDTO r:list
                 ) {
                obList.add(new RoomDTO(r.getRoom_id(),r.getType(),r.getKey_money(),r.getQty()));
                tblRoomType.setItems(obList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRoomDetails(String selectedId) {
        try {
            RoomDTO roomDTO = roomBO.searchRoom(selectedId);
            txtRoomId.setText(roomDTO.getRoom_id());
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(roomDTO.getKey_money());
            txtQtyOnHand.setText(String.valueOf(roomDTO.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRoomTypeIds() {
        try {
            cmbRoomId.setItems(FXCollections.observableArrayList(roomBO.getAllRoomIds()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnRoomSearchOnAction(ActionEvent actionEvent) {
        try {
            RoomDTO roomDTO =roomBO.searchRoom(txtRoomSearch.getText());
            txtRoomId.setText(roomDTO.getRoom_id());
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(roomDTO.getKey_money());
            txtQtyOnHand.setText(String.valueOf(roomDTO.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Empty result").show();

        }
    }

    public void btnAddNewRoomOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save New");
        //setEditable(true);
        txtQty.setEditable(false);
        txtQty.setDisable(true);
        txtQtyOnHand.setEditable(true);
        txtRoomType.setEditable(true);
        txtKeyMoney.setEditable(true);
        txtRoomId.setEditable(true);
        txtRoomId.setVisible(true);
        cmbRoomId.setVisible(false);
        clearText();

    }

    public void btnDeleteRoomOnAction(ActionEvent actionEvent) {
        try {
            roomBO.deleteRoom(cmbRoomId.getValue());
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!!").show();
            loadAllRoom();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save New")) {
            try {
                roomBO.addRoom(new RoomDTO(txtRoomId.getText(), txtRoomType.getText(), txtKeyMoney.getText(), Integer.valueOf(txtQtyOnHand.getText())));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
                loadAllRoom();
                clearText();
                txtRoomId.setVisible(false);
                cmbRoomId.setVisible(true);
                setEditable(false);
                txtQty.setEditable(true);
                txtQty.setDisable(false);
                btnSave.setText("Save");
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else if(btnSave.getText().equals("Save")) {
            int Qty = Integer.valueOf(txtQty.getText());
            int qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
            try {
                roomBO.AddNewRoomType(txtRoomId.getText(), (qtyOnHand + Qty));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
                loadAllRoom();
                clearText();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            try {
                roomBO.updateRoom(new RoomDTO(txtRoomId.getText(), txtRoomType.getText(), txtKeyMoney.getText(), Integer.valueOf(txtQtyOnHand.getText())));
                tblRoomType.refresh();
                //txtQty.setDisable(true);
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!!").show();
                txtRoomId.setVisible(false);
                cmbRoomId.setVisible(true);
                txtQty.setDisable(false);
                loadAllRoom();
                clearText();
                setEditable(false);
                txtQty.setEditable(true);
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void clearText(){
        txtKeyMoney.clear();
        txtRoomId.clear();
        txtRoomType.clear();
        txtQty.clear();
       // txtRoomSearch.clear();
        txtQtyOnHand.clear();
    }
    public void setEditable(boolean b){
        cmbRoomId.setEditable(false);
        txtQtyOnHand.setEditable(b);
        txtRoomType.setEditable(b);
        txtKeyMoney.setEditable(b);
        //txtQty.setText(b);
    }
}
