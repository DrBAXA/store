package sombra.comparator.article;

import sombra.entity.Article;

import java.util.Comparator;

public class ByPriceDown<T extends Article> implements Comparator<Article> {
    @Override
    public int compare(Article article1, Article article2) {
        if(article1.getPrice() > article2.getPrice()){
            return -1;
        }else if(article1.getPrice() < article2.getPrice()){
            return 1;
        }else {
            return 0;
        }
    }
}
