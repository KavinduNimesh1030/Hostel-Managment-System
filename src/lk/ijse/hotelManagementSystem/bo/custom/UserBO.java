package lk.ijse.hotelManagementSystem.bo.custom;

import lk.ijse.hotelManagementSystem.bo.SuperBO;
import lk.ijse.hotelManagementSystem.dto.StudentDTO;
import lk.ijse.hotelManagementSystem.dto.UserDTO;

public interface UserBO extends SuperBO {
    public boolean updateUser(UserDTO userDTO) throws Exception;
    public UserDTO searchUser(String id) throws Exception;
    public String searchUserId(String userName ,String password) throws Exception;
}
