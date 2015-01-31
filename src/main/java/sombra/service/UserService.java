package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.dao.ArticlesDAO;
import sombra.dao.RolesDAO;
import sombra.dao.UsersDAO;
import sombra.entity.Role;
import sombra.entity.User;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    RolesDAO rolesDAO;

    @Autowired
    ArticlesDAO articlesDAO;

    public User getUser(int id){
        return usersDAO.findOne(id);
    }

    public User getUser(String name){
        return usersDAO.findOne(name);
    }

    public void addUser(User user){
        user.setRegistrationDate(new Date());
        user.setRole(getDefaultRole());
        usersDAO.save(user);
    }

    public void updateUser(User user){
        usersDAO.save(user);
    }

    private Role getDefaultRole(){
        return rolesDAO.findOne("USER");
    }
}
