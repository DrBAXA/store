package sombra.util;

public class FilterOrder {
    private ArticleFilter filter;
    private  ArticleOrder order;

    public ArticleFilter getFilter() {
        return filter;
    }

    public void setFilter(ArticleFilter filter) {
        this.filter = filter;
    }

    public ArticleOrder getOrder() {
        return order;
    }

    public void setOrder(ArticleOrder order) {
        this.order = order;
    }
}
