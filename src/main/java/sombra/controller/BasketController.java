package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sombra.entity.Article;
import sombra.service.BasketService;
import sombra.service.EmailCreator;
import sombra.service.MailService;
import sombra.util.BasketInfo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    BasketService basketService;

    @Autowired
    MailService mailService;

    @Autowired
    EmailCreator emailCreator;

    @RequestMapping
    public String basket(ModelMap modelMap){
        modelMap.addAttribute("active", "basket");
        return "basket";
    }

    @RequestMapping(value="/info", method = RequestMethod.GET)
    public ResponseEntity<BasketInfo> getBasketInfo(Principal user,
                                                    HttpServletRequest request){
        String userName = user != null ? user.getName() : null;
        BasketInfo basketInfo = new BasketInfo(basketService.getBasket(userName, request));
        return new ResponseEntity<BasketInfo>(basketInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/{article_id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addArticle(@PathVariable("article_id") int articleId,
                                           Principal user,
                                           HttpServletRequest request) {
        String userName = user != null ? user.getName() : null;
        basketService.addToBasket(userName,request, articleId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{article_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFromBasket(@PathVariable("article_id") int articleId,
                                                 Principal user,
                                                 HttpServletRequest request) {
        String userName = user != null ? user.getName() : null;
        basketService.deleteFromBasket(userName, request, articleId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
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
