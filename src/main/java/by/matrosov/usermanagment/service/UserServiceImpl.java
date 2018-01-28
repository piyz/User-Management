package by.matrosov.usermanagment.service;

import by.matrosov.usermanagment.dao.GroupDao;
import by.matrosov.usermanagment.dao.UserDao;
import by.matrosov.usermanagment.model.Group;
import by.matrosov.usermanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> getByFirstname(String s) {
        return userDao.getByFirstname(s);
    }

    @Override
    public List<User> getByLastname(String s) {
        return userDao.getByLastname(s);
    }

    @Override
    public List<User> getByBirthday(Date d) {
        return userDao.getByBirthday(d);
    }

    @Override
    public User getByEmail(String s) {
        return userDao.getByEmail(s);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Group userGroup = groupDao.findByName("USER");
        user.setGroups(new HashSet<>(Arrays.asList(userGroup)));
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userDao.save(user);
    }

    @Override
    public void addAdminRole(User user) {
        Group userGroup = groupDao.findByName("ADMIN");
        user.setGroups(new HashSet<>(Arrays.asList(userGroup)));
        userDao.save(user);
    }

    @Override
    public void addUserRole(User user) {
        Group userGroup = groupDao.findByName("USER");
        user.setGroups(new HashSet<>(Arrays.asList(userGroup)));
        userDao.save(user);
    }

    @Override
    public User getByUsername(String s) {
        return userDao.getByUsername(s);
    }
}
