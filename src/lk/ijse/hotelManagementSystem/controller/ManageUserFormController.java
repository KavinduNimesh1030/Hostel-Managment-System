package lk.ijse.hotelManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.hotelManagementSystem.bo.BOFactory;
import lk.ijse.hotelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.ijse.hotelManagementSystem.bo.custom.impl.UserBOImpl;
import lk.ijse.hotelManagementSystem.dto.UserDTO;
import lk.ijse.hotelManagementSystem.util.ValidationUtil;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageUserFormController{
    private final UserBOImpl userBO = (UserBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public TextField txtUserName;
    public TextField txtCurrentPassword;
    public TextField txtReEnterPassword;
    public TextField pwdPassword;
    public PasswordField pwdPasswordField;
    public CheckBox checkBox;
    public TextField txtNewUserName;
    public Button btnUpdate;
    String password;


    public void initialize(){

    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();
        String password = txtCurrentPassword.getText();
        String userId = findUserId(userName ,password);
        try {
            userBO.updateUser(new UserDTO(userId ,txtNewUserName.getText(),txtReEnterPassword.getText()));
            new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
            clearText();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something Wrong").show();
        }
    }

    private String findUserId(String userName, String password) {
        String id =null;
        try {
            return id =  userBO.searchUserId(userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(id);
        return  id;
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

    public  void clearText(){
        txtNewUserName.clear();
        txtReEnterPassword.clear();
        txtUserName.clear();
        txtCurrentPassword.clear();
        pwdPassword.clear();
        pwdPasswordField.clear();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnOnKeyTyped(KeyEvent keyEvent) {
        password = pwdPasswordField.getText();
        System.out.println(password);
    }
}
