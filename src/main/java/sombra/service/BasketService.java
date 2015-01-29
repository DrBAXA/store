package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.entity.Article;
import sombra.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    public static String SESSION_ATTRIBUTE_BASKET = "basket";

    @Autowired
    ArticlesService articlesService;

    @Autowired
    UserService userService;

    public Map<Article, Integer> getBasket(String userName, HttpServletRequest request){
        Map<Article, Integer> basket = null;
        if(userName != null) {
            basket = userService.getUser(userName).getBasket();
        } else if(request.getSession().getAttribute(SESSION_ATTRIBUTE_BASKET) != null) {
            basket = (Map<Article, Integer>)request.getSession().getAttribute(SESSION_ATTRIBUTE_BASKET);
        } else{
            basket = new HashMap<>();
        }
        return basket;
    }

    public void addToBasket(String userName,HttpServletRequest request, int articleId){
        Map<Article, Integer> basket = getBasket(userName, request);
        Article article = articlesService.getArticle(articleId);
        if(basket.containsKey(article)){
            basket.put(article, basket.get(article)+1);
        } else {
            basket.put(article, 1);
        }
        updateBasket(userName, request, basket);
    }

    public void deleteFromBasket(String userName,HttpServletRequest request, int articleId){
        Map<Article, Integer> basket = getBasket(userName, request);
        Article article = articlesService.getArticle(articleId);
        basket.remove(article);
        updateBasket(userName, request, basket);
    }

    private void updateBasket(String userName, HttpServletRequest request, Map<Article, Integer> basket){
        if(userName != null) {
            User user = userService.getUser(userName);
            user.setBasket(basket);
            userService.updateUser(user);
        } else{
            request.getSession().setAttribute(SESSION_ATTRIBUTE_BASKET, basket);
        }
    }
}
