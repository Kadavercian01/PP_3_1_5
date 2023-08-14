package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

@Repository
public interface RoleDao {
    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getRoleById(long id);
}
