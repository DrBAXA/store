package sombra.comparator.article;

import sombra.entity.Article;
import sombra.util.ArticleField;

import java.util.Comparator;

public class ArticleComparatorFactory {
    public static Comparator<Article> getComparator(ArticleField OrderBy, boolean down){
        return new ByNameUp<Article>();
    }
}
