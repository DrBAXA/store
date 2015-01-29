package sombra.service;


import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sombra.entity.Article;
import sombra.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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

    @Before
    public void setup(){
        usersBasket.put(articleInBasket1, 1);
        usersBasket.put(articleInBasket2, 2);
        user.setBasket(usersBasket);
    }

    @Test
    public void addNewArticleTest(){
        when(userService.getUser(anyString())).thenReturn(user);
        when(articlesService.getArticle(anyInt())).thenReturn(article);

        basketService.addToBasket("user", 20);

        assertEquals(3, user.getBasket().size());
        assertEquals(new Integer(2), user.getBasket().get(articleInBasket2));
        assertEquals(new Integer(1), user.getBasket().get(articleInBasket1));
        assertEquals(new Integer(1), user.getBasket().get(article));
    }

    @Test
    public void addExistArticleTest(){
        when(userService.getUser(anyString())).thenReturn(user);
        when(articlesService.getArticle(anyInt())).thenReturn(articleInBasket2);

        basketService.addToBasket("user", 20);

        assertEquals(2, user.getBasket().size());
        assertEquals(new Integer(3), user.getBasket().get(articleInBasket2));
        assertEquals(new Integer(1), user.getBasket().get(articleInBasket1));
    }

}
