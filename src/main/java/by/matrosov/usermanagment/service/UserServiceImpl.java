package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.dao.GroupDao;
import by.matrosov.usermanagment.dao.UserDao;
import by.matrosov.usermanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Override
    public User getById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public User getByFirstname(String s) {
        return userDao.getByFirstname(s);
    }

    @Override
    public User getByLastname(String s) {
        return userDao.getByLastname(s);
    }

    @Override
    public User getByBirthday(Date d) {
        return userDao.getByBirthday(d);
    }
}
