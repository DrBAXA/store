package sombra.comparator.article;

import sombra.entity.Article;

import java.util.Comparator;

public class ByNameDown implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Article article1 = (Article)o1;
        Article article2 = (Article)o2;
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
