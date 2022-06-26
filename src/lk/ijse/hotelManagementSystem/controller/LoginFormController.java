package lk.ijse.hotelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class LoginFormController {
    public TextField txtUserName;
    public PasswordField pwdPasswordField;
    public TextField pwdPassword;
    public CheckBox checkBox;
    private  int attempts;

    public void userLoginOnAction(ActionEvent actionEvent) throws IOException {
        attempts++;
        if (pwdPassword.getText().equals("")) {
            pwdPassword.setText(pwdPasswordField.getText());

        } else if (pwdPasswordField.getText().equals("")) {
            pwdPasswordField.setText(pwdPassword.getText());

        }

        pwdPassword.setText(pwdPasswordField.getText());
        if (attempts <= 3) {
            if (txtUserName.getText().equals("user") && pwdPassword.getText().equals("1234")) {
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


            } else {
                new Alert(Alert.AlertType.WARNING, "Try again!").show();

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
}
