package sombra.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.comparator.article.ArticleComparatorFactory;
import sombra.dao.ArticlesDAO;
import sombra.entity.Article;
import sombra.util.ArticleField;

import java.util.*;

@Service
public class ArticlesService {

    @Autowired
    ArticlesDAO articlesDAO;

    public List<Article> getAll(ArticleField orderBy, boolean down,  int first, int last){
        List<Article> result = new ArrayList<>();
        Iterable<Article> all =  articlesDAO.findAll();
        List fromDB = new ArrayList();
        for(Article article:all){
            fromDB.add(article);
        }
        Comparator<Article> comparator = ArticleComparatorFactory.getComparator(ArticleField.NAME, false);
        Collections.sort(fromDB, comparator);
        Iterator<Article> iterator = fromDB.iterator();
        for(int i = 0; i<fromDB.size(); i++){
            if(i>=first && i < last ){
                result.add(iterator.next());
            }else {
                iterator.next();
            }
        }
        return result;
    }
}
