package sombra.comparator.article;

import sombra.entity.Article;
import sombra.util.ArticleField;

import java.util.Comparator;

import static sombra.util.ArticleField.*;

public class ArticleComparatorFactory {
    public static Comparator<Article> getComparator(ArticleField orderBy, boolean down){
        if(orderBy == NAME){
            if(down){
                return new ByNameDown<>();
            }else {
                return new ByNameUp<>();
            }
        }else if(orderBy == PRICE){
            if(down){
                return new ByPriceDown<>();
            }else {
                return new ByPriceUp<>();
            }
        }else {
            throw new IllegalArgumentException("No comparator for field " + orderBy.toString());
        }
    }
}
