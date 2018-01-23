package by.matrosov.usermanagment.dao;

import by.matrosov.usermanagment.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Group,Long> {
}
