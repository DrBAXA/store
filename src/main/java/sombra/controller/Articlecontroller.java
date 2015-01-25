package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sombra.service.ArticlesService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping("/{id}")
    public String getArticle(ModelMap model, @PathVariable("id") int id){
        model.addAttribute("article", articlesService.getArticle(id));
        return "article";
    }
}
