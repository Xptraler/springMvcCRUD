package ru.javamentor.dao;

import ru.javamentor.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void createUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id", (long) id).executeUpdate();


    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User getUser(int id) {
        User user = (User) entityManager.createQuery("select u from User u where u.id = :id")
                .setParameter("id", (long) id).getSingleResult();
        return user;

    }

    @Override
    public void update(int id, User user1) {
        User updatedUser = getUser(id);
        updatedUser.setName(user1.getName());
        updatedUser.setPassword(user1.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        User user = (User) entityManager.createQuery("select u from User u where u.name = :username")
                .setParameter("username", username).getSingleResult();
        return user;
    }
}