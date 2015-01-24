package sombra.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sombra.service.ArticlesService;
import sombra.util.ArticleField;
import sombra.util.PaginationResult;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PaginationResult> getAll(@RequestParam("first") int first,
                                                @RequestParam("count") int count,
                                                @RequestParam("orderby")ArticleField orderBy,
                                                @RequestParam("down") boolean down
                                                ){
        PaginationResult result = articlesService.getAll(orderBy, down, first,count);
        return new ResponseEntity<PaginationResult>(result, HttpStatus.OK);
    }
}
