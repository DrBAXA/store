package sombra.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sombra.entity.Article;
import sombra.entity.User;
import sombra.service.ArticlesService;
import sombra.service.BasketService;
import sombra.service.UserService;
import sombra.util.BasketInfo;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticlesService articlesService;

    @Autowired
    BasketService basketService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> getUser(Principal user) {
        if (user != null) {
            return new ResponseEntity<User>(userService.getUser(user.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/basket/{article_id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addArticle(@PathVariable("article_id") int articleId,
                                           Principal user,
                                           HttpServletRequest request) {
        if (user != null) {
            basketService.addToBasket(user.getName(), articleId);
        }
        BasketInfo sessionBasket = (BasketInfo) request.getSession().getAttribute("basket");
        if (sessionBasket == null) {
            sessionBasket = new BasketInfo();
        }
        sessionBasket.addToBasket(articlesService.getArticle(articleId));
        request.getSession().setAttribute("basket", sessionBasket);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/basket_info", method = RequestMethod.GET)
    public ResponseEntity<BasketInfo> getBasketInfo(Principal principal,
                                                    HttpServletRequest request) {
        BasketInfo basketInfo;
        if (principal != null) {
            User user = userService.getUser(principal.getName());
            basketInfo = new BasketInfo(user.getBasket());
            if (request.getSession().getAttribute("basket") == null) {
                request.getSession().setAttribute("basket", basketInfo);
            }
            return new ResponseEntity<BasketInfo>(basketInfo, HttpStatus.OK);
        }
        if (request.getSession().getAttribute("basket") != null) {
            return new ResponseEntity<BasketInfo>((BasketInfo) request.getSession().getAttribute("basket"), HttpStatus.OK);
        } else {
            return new ResponseEntity<BasketInfo>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ResponseEntity<Map<Article, Integer>> getBasket(Principal principal,
                                                           HttpServletRequest request) {
        if (principal != null) {
            User user = userService.getUser(principal.getName());
            return new ResponseEntity<Map<Article, Integer>>(user.getBasket(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<Article, Integer>>(basketService.getSessionBasket(request), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/basket/{article_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFromBasket(@PathVariable("article_id") int articleId,
                                                 Principal principal,
                                                 HttpServletRequest request) {
        if (principal != null) {
            basketService.deleteFromBasket(principal.getName(), articleId);
        }
        BasketInfo sessionBasket = (BasketInfo) request.getSession().getAttribute("basket");
        if (sessionBasket != null) {
            sessionBasket.getArticles().remove(new Integer(articleId));
            int updatedPrice = sessionBasket.getPrice() - articlesService.getArticle(articleId).getPrice();
            sessionBasket.setPrice(updatedPrice);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
