package lk.ijse.hotelManagementSystem.bo.custom.impl;

import lk.ijse.hotelManagementSystem.bo.custom.UserBO;
import lk.ijse.hotelManagementSystem.dao.DAOFactory;
import lk.ijse.hotelManagementSystem.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hotelManagementSystem.dao.custom.impl.UserDAOImpl;
import lk.ijse.hotelManagementSystem.dto.UserDTO;
import lk.ijse.hotelManagementSystem.entity.User;


public class UserBOImpl implements UserBO {
    private final UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getPassword()));
    }

    @Override
    public UserDTO searchUser(String id) throws Exception {
        return null;
    }

    @Override
    public String searchUserId(String userName, String password) throws Exception {
        return userDAO.find(userName , password);
    }
}
