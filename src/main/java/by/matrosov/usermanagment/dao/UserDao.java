package by.matrosov.usermanagment.dao;

import by.matrosov.usermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
}
