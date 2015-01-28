package sombra.util;

import sombra.entity.Article;

import java.util.HashMap;
import java.util.Map;

public class BasketInfo {
    private Map<Integer, Integer> articles = new HashMap<>();
    private int price;

    public BasketInfo() {
    }

    public BasketInfo(Map<Article, Integer> basket) {
        for(Article article : basket.keySet()){
            articles.put(article.getId(), basket.get(article));
            price += article.getPrice()*basket.get(article);
        }
    }

    public void addToBasket(Article article){
        if(articles.containsKey(article.getId())){
            articles.put(article.getId(), articles.get(article.getId())+1);
        } else {
            articles.put(article.getId(), 1);
        }
        price += article.getPrice();
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
