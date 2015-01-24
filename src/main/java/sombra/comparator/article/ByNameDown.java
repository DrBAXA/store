package sombra.comparator.article;

import sombra.entity.Article;

import java.util.Comparator;

public class ByNameDown<T extends Article> implements Comparator<Article> {
    @Override
    public int compare(Article article1, Article article2) {
        int compareIn = article1.getName().compareTo(article2.getName());
        if(compareIn == 0){
            return 0;
        }else if(compareIn > 0){
            return -1;
        }else {
            return 1;
        }
    }
}
