package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.model.User;

import java.sql.Date;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(long id);
    List<User> getByFirstname(String s);
    List<User> getByLastname(String s);
    List<User> getByBirthday(Date d);
    User getByEmail(String s);
    void saveUser(User user);
    void updateUser(User user);
    void addAdminRole(User user);
    void addUserRole(User user);
    User getByUsername(String s);
    List<User> getAllByActive(int a);
}
