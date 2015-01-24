package sombra.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sombra.entity.Article;
import sombra.service.ArticlesService;
import sombra.util.ArticleField;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getAll(@RequestParam("first") int first,
                                                @RequestParam("last") int last,
                                                @RequestParam("orderby")ArticleField orderBy,
                                                @RequestParam("down") boolean down
                                                ){
        List<Article> result = articlesService.getAll(ArticleField.NAME, false, 0,5);
        return new ResponseEntity<List<Article>>(result, HttpStatus.OK);
    }
}
