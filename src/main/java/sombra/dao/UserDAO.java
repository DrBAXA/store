package sombra.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sombra.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAO implements IUserDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void addUser(String name, String email,String password){
        User user = new User(name, email, password);
        entityManager.persist(user);
    }
}
