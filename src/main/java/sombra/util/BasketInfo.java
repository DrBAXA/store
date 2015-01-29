package sombra.util;

import sombra.entity.Article;

import java.util.HashMap;
import java.util.Map;

public class BasketInfo {
    private Map<Integer, Integer> articles = new HashMap<>();
    private int price;

    public BasketInfo(Map<Article, Integer> basket) {
        for(Article article : basket.keySet()){
            articles.put(article.getId(), basket.get(article));
            price += article.getPrice()*basket.get(article);
        }
    }

    public Map<Integer, Integer> getArticles() {
        return articles;
    }

    public void setArticles(Map<Integer, Integer> articles) {
        this.articles = articles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
