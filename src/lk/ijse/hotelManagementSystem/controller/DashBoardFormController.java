package lk.ijse.hotelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotelManagementSystem.util.Loader;

import java.io.IOException;

public class DashBoardFormController implements Loader {
    public AnchorPane context;

    public void initialize() throws IOException {
        loadUi("DashBoardViewForm");
    }

    public void btnOpenDashboardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DashBoardViewForm");
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddRoomForm");
    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddStudentForm");
    }
    public void btnAddReservationOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddReservationForm");
    }
    public void btnViewReservationOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageKeyMoneyForm");
    }

    @Override
    public void loadUi(String Location) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+Location+".fxml"));
        context.getChildren().add(parent);
    }



}
