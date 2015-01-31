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
import sombra.entity.Category;
import sombra.service.ArticlesService;
import sombra.util.ArticleField;
import sombra.util.PaginationResult;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleRESTController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PaginationResult> getAll(@RequestParam("first") int first,
                                                @RequestParam("count") int count,
                                                @RequestParam("orderby")ArticleField orderBy,
                                                @RequestParam("decrease") boolean decrease
                                                ){
        PaginationResult result = articlesService.getAll(orderBy, decrease, first,count);
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
}
