package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.model.User;

import java.sql.Date;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(long id);
    User getByFirstname(String s);
    User getByLastname(String s);
    User getByBirthday(Date d);
    User getByEmail(String s);
    void saveUser(User user);
    void updateUser(User user);
    void addAdminRole(User user);
}
