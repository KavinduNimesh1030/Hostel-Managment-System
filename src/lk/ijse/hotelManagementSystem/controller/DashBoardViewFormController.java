package lk.ijse.hotelManagementSystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.net.URL;

public class DashBoardViewFormController {
    private final UserBOImpl userBO = (UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public Text txtName;
    public Text lblUserName;

    public  void initialize(){
      
        lblUserName.setText(LoginFormController.getUserName());
        txtName.setText(LoginFormController.getUserName()+"!");
        System.out.println("inDashBoard"+LoginFormController.getUserName());
    }

    public void btnOpenAddUserForm(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageUserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
