package by.matrosov.usermanagment.dao;

import by.matrosov.usermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;


public interface UserDao extends JpaRepository<User,Long>{
    User getByFirstname(String s);
    User getByLastname(String s);
    User getByBirthday(Date d);
    User getByEmail(String s);
}
