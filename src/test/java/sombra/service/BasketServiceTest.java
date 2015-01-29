package sombra.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import sombra.entity.Article;
import sombra.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {
    @Mock
    UserService userService;
    @Mock
    ArticlesService articlesService;
    @InjectMocks
    BasketService basketService;
    Article articleInBasket1 = new Article("BasketArticle1", 500);
    Article articleInBasket2 = new Article("BasketArticle2", 1000);
    Article article = new Article("Article", 800);
    Map<Article, Integer> usersBasket = new HashMap<>();
    User user = new User("user", "user@mail.com", "password", new Date());
    HttpServletRequest request = new MockHttpServletRequest();
    @Before
    public void setup(){
        usersBasket.put(articleInBasket1, 1);
        usersBasket.put(articleInBasket2, 2);
        user.setBasket(usersBasket);
    }

    /*
    Adding new article to basket of logged user
     */
    @Test
    public void addNewArticleLoggedUserTest(){
        when(userService.getUser(anyString())).thenReturn(user);
        when(articlesService.getArticle(anyInt())).thenReturn(article);

        basketService.addToBasket("user", request, 20);

        assertEquals(3, user.getBasket().size());
        assertEquals(new Integer(2), user.getBasket().get(articleInBasket2));
        assertEquals(new Integer(1), user.getBasket().get(articleInBasket1));
        assertEquals(new Integer(1), user.getBasket().get(article));
    }

    /*
    Adding exist article to basket of logged user
     */
    @Test
    public void addExistArticleLoggedUserTest(){
        when(userService.getUser(anyString())).thenReturn(user);
        when(articlesService.getArticle(anyInt())).thenReturn(articleInBasket2);

        basketService.addToBasket("user",request, 20);

        assertEquals(2, user.getBasket().size());
        assertEquals(new Integer(3), user.getBasket().get(articleInBasket2));
        assertEquals(new Integer(1), user.getBasket().get(articleInBasket1));
    }

    @Test
    public void addNewArticleUnloggedUserTest(){
        when(articlesService.getArticle(anyInt())).thenReturn(article);
        request.getSession().setAttribute("basket", usersBasket);

        basketService.addToBasket(null,request, 20);

        Map<Article, Integer> sessionBasket = ((Map)request.getSession().getAttribute("basket"));
        assertEquals(3, sessionBasket.size());
        assertEquals(new Integer(2), sessionBasket.get(articleInBasket2));
        assertEquals(new Integer(1), sessionBasket.get(articleInBasket1));
        assertEquals(new Integer(1), sessionBasket.get(article));
    }

    @Test
    public void addExistArticleUnloggedUserTest(){
        when(articlesService.getArticle(anyInt())).thenReturn(articleInBasket2);
        request.getSession().setAttribute("basket", usersBasket);

        basketService.addToBasket(null,request, 20);

        Map<Article, Integer> sessionBasket = ((Map)request.getSession().getAttribute("basket"));
        assertEquals(2, sessionBasket.size());
        assertEquals(new Integer(3), sessionBasket.get(articleInBasket2));
        assertEquals(new Integer(1), sessionBasket.get(articleInBasket1));
    }

    @Test
    public void addFirstArticleUnloggedUserTest(){
        when(articlesService.getArticle(anyInt())).thenReturn(article);
        request.getSession().removeAttribute("basket");

        basketService.addToBasket(null, request, 20);

        Map<Article, Integer> sessionBasket = ((Map)request.getSession().getAttribute("basket"));
        assertEquals(1, sessionBasket.size());
        assertEquals(new Integer(1), sessionBasket.get(article));
    }

    @Test
    public void deleteArticleUnloggedUserTest(){
        when(articlesService.getArticle(anyInt())).thenReturn(articleInBasket1);
        request.getSession().setAttribute("basket", usersBasket);

        basketService.deleteFromBasket(null, request, 20);

        Map<Article, Integer> sessionBasket = ((Map)request.getSession().getAttribute("basket"));
        assertEquals(1, sessionBasket.size());
        assertEquals(new Integer(2), sessionBasket.get(articleInBasket2));
        assertNull(sessionBasket.get(articleInBasket1));
    }

    @Test
    public void deleteArticleLoggedUserTest(){
        when(articlesService.getArticle(anyInt())).thenReturn(articleInBasket1);
        when(userService.getUser(anyString())).thenReturn(user);

        basketService.deleteFromBasket("user", request, 20);

        assertEquals(1, user.getBasket().size());
        assertEquals(new Integer(2), user.getBasket().get(articleInBasket2));
        assertNull(user.getBasket().get(articleInBasket1));
    }


}
