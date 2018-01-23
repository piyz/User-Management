package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.dao.GroupDao;
import by.matrosov.usermanagment.dao.UserDao;
import by.matrosov.usermanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
