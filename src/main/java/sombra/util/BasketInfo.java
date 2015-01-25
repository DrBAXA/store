package sombra.util;

import sombra.entity.Article;

import java.util.LinkedList;
import java.util.List;

public class BasketInfo {
    private List<Integer> articles = new LinkedList<>();
    private int price;

    public BasketInfo() {
    }

    public BasketInfo(List<Article> basket) {
        for(Article article : basket){
            articles.add(article.getId());
            price += article.getPrice();
        }
    }

    public void addToBasket(Article article){
        articles.add(article.getId());
        price += article.getPrice();
    }

    public List<Integer> getArticles() {
        return articles;
    }

    public void setArticles(List<Integer> articles) {
        this.articles = articles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
