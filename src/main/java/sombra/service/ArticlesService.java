package sombra.service;


import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.comparator.article.ArticleComparatorFactory;
import sombra.dao.ArticlesDAO;
import sombra.entity.Article;
import sombra.util.ArticleField;
import sombra.util.PaginationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ArticlesService {

    @Autowired
    ArticlesDAO articlesDAO;

    public PaginationResult getAll(ArticleField orderBy, boolean down,  int first, int count){
        PaginationResult result = new PaginationResult();
        List<Article> allArticles = new ArrayList<>();
        allArticles.addAll(IteratorUtils.toList(articlesDAO.findAll().iterator()));
        result.setCountAll(allArticles.size());
        Collections.sort(allArticles, ArticleComparatorFactory.getComparator(orderBy, down));
        if(first > allArticles.size()){
            throw new IllegalArgumentException("No result for index "+first);
        };
        int last = first+count;
        if(last > allArticles.size()){
            last = allArticles.size();
        }
        result.setPageData(allArticles.subList(first, last));
        return result;
    }
}
