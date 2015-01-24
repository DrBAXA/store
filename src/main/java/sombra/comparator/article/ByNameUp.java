package sombra.comparator.article;

import sombra.entity.Article;

import java.util.Comparator;

public class ByNameUp<T extends Article> implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Article article1 = (Article)o1;
        Article article2 = (Article)o2;
        return article1.getName().compareTo(article2.getName());
    }
}
