package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(long id);
    User getByFirstname(String s);
    User getByLastname(String s);
}
