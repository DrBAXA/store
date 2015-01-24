package sombra.comparator.article;

import sombra.entity.Article;

import java.util.Comparator;

public class ByNameUp<T extends Article> implements Comparator<Article> {
    @Override
    public int compare(Article article1, Article article2) {
        return article1.getName().compareTo(article2.getName());
    }
}
