package ru.javamentor.dao;

import org.springframework.stereotype.Repository;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getOneRole(int id) {
        Role role = (Role) entityManager.createQuery("select u from Role u where u.id = :id")
                .setParameter("id",(long)id).getSingleResult();
        return role;
    }
}
