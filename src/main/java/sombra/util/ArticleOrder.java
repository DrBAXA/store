package sombra.util;

public class ArticleOrder {
    private int first;
    private int count;
    private ArticleField orderBy;
    private boolean decrease;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArticleField getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(ArticleField orderBy) {
        this.orderBy = orderBy;
    }

    public boolean getDecrease() {
        return decrease;
    }

    public void setDecrease(boolean decrease) {
        this.decrease = decrease;
    }
}
