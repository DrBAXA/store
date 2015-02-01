package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sombra.dao.ArticlesDAO;
import sombra.dao.RolesDAO;
import sombra.dao.UsersDAO;
import sombra.entity.Role;
import sombra.entity.User;

import java.util.Date;

@Service
public class UserService {

    public static final String DEFAULT_USER_ROLE_NAME = "ROLE_USER";

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    RolesDAO rolesDAO;

    @Autowired
    ArticlesDAO articlesDAO;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUser(int id){
        return usersDAO.findOne(id);
    }

    public User getUser(String name){
        return usersDAO.findOne(name);
    }

    public void addUser(User user){
        user.setRegistrationDate(new Date());
        user.setRole(getDefaultRole());
        String rawPassword = user.getPassword();
        String codedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(codedPassword);
        usersDAO.save(user);
    }

    public void updateUser(User user){
        usersDAO.save(user);
    }

    private Role getDefaultRole(){
        return rolesDAO.findOne(DEFAULT_USER_ROLE_NAME);
    }
}
