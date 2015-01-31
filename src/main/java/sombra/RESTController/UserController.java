package sombra.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sombra.entity.Article;
import sombra.entity.User;
import sombra.service.*;
import sombra.util.BasketInfo;

import javax.mail.MessagingException;
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

    @Autowired
    MailService mailService;

    @Autowired
    EmailCreator emailCreator;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> getUser(Principal user) {
        if (user != null) {
            return new ResponseEntity<User>(userService.getUser(user.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/basket_info", method = RequestMethod.GET)
    public ResponseEntity<BasketInfo> getBasketInfo(Principal user,
                                                    HttpServletRequest request){
        String userName = user != null ? user.getName() : null;
        BasketInfo basketInfo = new BasketInfo(basketService.getBasket(userName, request));
        return new ResponseEntity<BasketInfo>(basketInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/basket/{article_id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addArticle(@PathVariable("article_id") int articleId,
                                           Principal user,
                                           HttpServletRequest request) {
        String userName = user != null ? user.getName() : null;
        basketService.addToBasket(userName,request, articleId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/basket/{article_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFromBasket(@PathVariable("article_id") int articleId,
                                                 Principal user,
                                                 HttpServletRequest request) {
        String userName = user != null ? user.getName() : null;
        basketService.deleteFromBasket(userName, request, articleId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/basket/buy", method = RequestMethod.POST)
    public ResponseEntity<Void> doBuy(@RequestParam("email") String email,
                                      @RequestParam("phone") String phone,
                                      Principal user,
                                      HttpServletRequest request) throws MessagingException {
        String userName = user != null ? user.getName() : null;
        Map<Article, Integer> basket = basketService.getBasket(userName, request);
        mailService.sendMail(email,"Замовлення на SombraStore", emailCreator.buyEmail(basket, phone));
        basketService.buy(userName,email, phone, request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
