package sombra.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sombra.entity.Article;
import sombra.entity.Category;
import sombra.service.ArticlesService;
import sombra.util.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleRESTController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PaginationResult> getAll(@RequestBody FilterOrder filterOrder){
        PaginationResult result = articlesService.getAll(filterOrder);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") int id){
        return new ResponseEntity<>(articlesService.getArticle(id),HttpStatus.OK);
    }

    @RequestMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(articlesService.getRootCategories(), HttpStatus.OK);
    }

    @RequestMapping(value = "price_limit", method = RequestMethod.POST)
    public ResponseEntity<PriceLimit> getPriceLimit(@RequestBody ArticleFilter filter){
        //articlesService.getPriseLimit(filter);
        return new ResponseEntity<PriceLimit>(articlesService.getPriseLimit(filter), HttpStatus.OK);
    }
}
