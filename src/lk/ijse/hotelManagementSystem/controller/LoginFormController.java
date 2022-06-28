package lk.ijse.hotelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class LoginFormController {
    private final UserBOImpl userBO = (UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public TextField txtUserName;
    public PasswordField pwdPasswordField;
    public TextField pwdPassword;
    public CheckBox checkBox;
    private  int attempts;
    private  static String userName;

    public void userLoginOnAction(ActionEvent actionEvent) throws IOException {
        attempts++;
        if (pwdPassword.getText().equals("")) {
            pwdPassword.setText(pwdPasswordField.getText());

        } else if (pwdPasswordField.getText().equals("")) {
            pwdPasswordField.setText(pwdPassword.getText());

        }

        pwdPassword.setText(pwdPasswordField.getText());
        if (attempts <= 3) {
            try {
               String id = userBO.searchUserId(txtUserName.getText(),pwdPassword.getText());
               if(id!=null){
                   //userName = txtUserName.getText();
                   setUserName(txtUserName.getText());
                   System.out.println(getUserName());
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                           "Login Success",
                           ButtonType.OK, ButtonType.CANCEL);

                   Optional<ButtonType> buttonType = alert.showAndWait();
                   if (buttonType.get().equals(ButtonType.OK)) {
                       URL resource = getClass().getResource("../view/DashBoardForm.fxml");
                       Parent load = FXMLLoader.load(resource);
                       Scene scene = new Scene(load);
                       Stage stage = new Stage();
                       stage.setScene(scene);
                       stage.show();
                   }

               }else {
                   new Alert(Alert.AlertType.WARNING, "Try again!").show();
               }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            txtUserName.setEditable(false);
            pwdPassword.setEditable(false);

        }

    }

    public void checkBoxOnAction(ActionEvent actionEvent) {
        if (checkBox.isSelected()) {
            pwdPassword.setText(pwdPasswordField.getText());
            System.out.println("Select");
            pwdPasswordField.setVisible(false);
            pwdPassword.setVisible(true);
        } else {
            System.out.println("not");
            pwdPasswordField.setText(pwdPassword.getText());
            pwdPasswordField.setVisible(true);
            pwdPassword.setVisible(false);
        }


    }
    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        LoginFormController.userName = userName;
    }


}
