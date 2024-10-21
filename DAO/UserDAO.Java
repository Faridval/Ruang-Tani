package dao;

import java.util.List;
import model.User;

public interface UserDAO {
    void signUp(User user);
    User login(String username, String password);
    void logout(User user);
    List<User> getAllUsers();
}
