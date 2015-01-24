package sombra.util;

import sombra.entity.Article;

import java.util.List;

public class PaginationResult {
    private List<Article> pageData;
    private int countAll;

    public List<Article> getPageData() {
        return pageData;
    }

    public void setPageData(List<Article> pageData) {
        this.pageData = pageData;
    }

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }
}

