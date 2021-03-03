package ru.javamentor.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.javamentor.dao.RoleDao;
import ru.javamentor.dao.UserDao;
import ru.javamentor.dao.UserDaoImpl;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;


    @Autowired
    public UserServiceImpl(UserDao userDao,RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Transactional
    @Override
    public void createUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOneRole(1));
        user.setRoles(roles);
        userDao.createUser(user);

    }

    @Override
    @Transactional
    @Modifying
    public void deleteUser(int id) {
        userDao.deleteUser(id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();

    }

    @Override
    @Transactional
    public User getUser(int id) {
       return userDao.getUser(id);

    }

    @Override
    @Transactional
    @Modifying
    public void update(int id, User user) {
        userDao.update(id,user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
