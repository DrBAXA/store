package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sombra.dao.ArticlesDAO;
import sombra.dao.RolesDAO;
import sombra.dao.UsersDAO;
import sombra.entity.Article;
import sombra.entity.Role;
import sombra.entity.User;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Qualifier("UsersDAO")
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

    public void addToBasket(String userName, int articleId){
        User user = usersDAO.findOne(userName);
        List<Article> basket = user.getBasket();
        basket.add(articlesDAO.findOne(articleId));
        usersDAO.save(user);
    }

    public void deleteFromBasket(String userName, int articleId){
        User user = usersDAO.findOne(userName);
        List<Article> basket = user.getBasket();
        basket.remove(articlesDAO.findOne(articleId));
        usersDAO.save(user);
    }

    private Role getDefaultRole(){
        return rolesDAO.findOne("USER");
    }
}
