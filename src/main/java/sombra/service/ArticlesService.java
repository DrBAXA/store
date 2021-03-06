package sombra.service;


import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sombra.comparator.article.ArticleComparatorFactory;
import sombra.dao.ArticlesDAO;
import sombra.dao.CategoriesDAO;
import sombra.entity.Article;
import sombra.entity.Category;
import sombra.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticlesService {

    @Autowired
    ArticlesDAO articlesDAO;
    @Autowired
    CategoriesDAO categoriesDAO;

    public PaginationResult getAll(FilterOrder filterOrder){
        PaginationResult result = new PaginationResult();
        List<Article> allArticles = new ArrayList<>();
        ArticleFilter filter = filterOrder.getFilter();
        Iterable<Article> queryResult =  articlesDAO.getByFilter(filter.getCategories(), filter.getPriceMin(), filter.getPriceMax());
        allArticles.addAll(IteratorUtils.toList(queryResult.iterator()));
        result.setCountAll(allArticles.size());
        ArticleOrder order = filterOrder.getOrder();
        Collections.sort(allArticles, ArticleComparatorFactory.getComparator(order.getOrderBy(), order.getDecrease()));
        if(order.getFirst() > allArticles.size()){
            throw new IllegalArgumentException("No result for index "+order.getFirst());
        }
        int last = order.getFirst()+order.getCount();
        if(last > allArticles.size()){
            last = allArticles.size();
        }
        result.setPageData(allArticles.subList(order.getFirst(), last));
        return result;
    }

    public Article getArticle(int id){
        return articlesDAO.findOne(id);
    }

    public List<Category> getRootCategories(){
        List<Category> categories = new LinkedList<>();
        for(Category category : categoriesDAO.getRootCategories()){
            categories.add(category);
        }
        return categories;
    }

    public PriceLimit getPriseLimit(ArticleFilter filter){
        int max = articlesDAO.getMaxPrice(filter.getCategories());
        int min = articlesDAO.getMinPrice(filter.getCategories());
        return new PriceLimit(min, max);
    }

}
