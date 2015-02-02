package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sombra.entity.Article;
import sombra.entity.Category;
import sombra.service.ArticlesService;
import sombra.util.ArticleFilter;
import sombra.util.FilterOrder;
import sombra.util.PaginationResult;
import sombra.util.PriceLimit;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PaginationResult> getAll(@RequestBody FilterOrder filterOrder){
        PaginationResult result = articlesService.getAll(filterOrder);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getArticle(@PathVariable("id") int id,
                             ModelMap modelMap,
                             Principal user){
        if(user != null){
            modelMap.addAttribute("user", user.getName());
        }
        modelMap.addAttribute("article",articlesService.getArticle(id));
        return "article";
    }

    @RequestMapping(value = "/{id}/json", method = RequestMethod.GET)
    public ResponseEntity<Article> getArticle(@PathVariable("id") int id){
        return new ResponseEntity<>(articlesService.getArticle(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> daleteArticle(@PathVariable("id") int id){
        try {
            articlesService.deleteArticle(id);
        }catch (JpaSystemException pe){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(articlesService.getRootCategories(), HttpStatus.OK);
    }

    @RequestMapping(value = "price_limit", method = RequestMethod.POST)
    public ResponseEntity<PriceLimit> getPriceLimit(@RequestBody ArticleFilter filter){
        return new ResponseEntity<PriceLimit>(articlesService.getPriseLimit(filter), HttpStatus.OK);
    }
}
