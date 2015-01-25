package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sombra.service.ArticlesService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping("/details")
    public String getArticle(){
        return "article";
    }
}
