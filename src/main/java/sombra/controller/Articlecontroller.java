package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sombra.service.ArticlesService;

import java.security.Principal;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping("/details")
    public String getArticle(ModelMap modelMap, Principal user){
        if(user != null){
            modelMap.addAttribute("user", user.getName());
        }
        return "article";
    }
}
