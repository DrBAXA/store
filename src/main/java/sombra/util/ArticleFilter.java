package sombra.util;

/**
 * Created by DrBAX_000 on 24.01.2015.
 */
public class ArticleFilter {
    private int first;
    private int last;
    private ArticleField orderBy;
    private boolean direct;

    public void first(int value){
        first = value;
    }

    public void last(int value){
        last = value;
    }

    public void orderBy(ArticleField field){
        orderBy = field;
    }

    public  void down(boolean value){
        direct = value;
    }

    public ArticleFilter get(){
        return new ArticleFilter();
    }
}
