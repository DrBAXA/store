package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.entity.Article;
import sombra.entity.User;
import sombra.util.BasketInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    @Autowired
    ArticlesService articlesService;

    @Autowired
    UserService userService;



    public void addToBasket(String userName, int articleId){
        User user = userService.getUser(userName);
        Map<Article, Integer> basket = user.getBasket();
        Article article = articlesService.getArticle(articleId);
        if(basket.containsKey(article)){
            basket.put(article, basket.get(article)+1);
        } else {
            basket.put(article, 1);
        }
        userService.updateUser(user);
    }

    //TODO implement counting
    public void deleteFromBasket(String userName, int articleId){
        User user = userService.getUser(userName);
        Map<Article, Integer> basket = user.getBasket();
        basket.remove(articlesService.getArticle(articleId));
        userService.updateUser(user);
    }

    public Map<Article, Integer> getSessionBasket(HttpServletRequest request) {
        Map<Article, Integer> basket = new HashMap<>();
        BasketInfo basketInfo = (BasketInfo)request.getSession().getAttribute("basket");
        if(basketInfo != null){
            for(Integer id: basketInfo.getArticles().keySet()){
                basket.put(articlesService.getArticle(id), basketInfo.getArticles().get(id));
            }
        }
        return null;
    }

}
