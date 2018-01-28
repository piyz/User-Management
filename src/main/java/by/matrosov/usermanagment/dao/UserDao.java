package by.matrosov.usermanagment.dao;

import by.matrosov.usermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;


public interface UserDao extends JpaRepository<User,Long>{
    List<User> getByFirstname(String s);
    List<User> getByLastname(String s);
    List<User> getByBirthday(java.util.Date birthday);
    User getByEmail(String s);
    User getByUsername(String s);
}
