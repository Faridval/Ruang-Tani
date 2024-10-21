package controller;

import model.User;
import dao.UserDAO;
import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void signUp(User user) {
        userDAO.signUp(user);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public void logout(User user) {
        userDAO.logout(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
