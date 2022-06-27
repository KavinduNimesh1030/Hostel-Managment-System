package lk.ijse.hotelManagementSystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardViewFormController {

    public Text txtName;
    public Text lblUserName;

    public void btnOpenAddUserForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageUserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
