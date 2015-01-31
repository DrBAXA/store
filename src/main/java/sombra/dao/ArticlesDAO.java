package sombra.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sombra.entity.Article;

import java.util.Collection;
import java.util.List;

public interface ArticlesDAO extends CrudRepository<Article, Integer>{
    @Query(value = "SELECT max(price) FROM  articles WHERE category IN ?1", nativeQuery = true)
    int getMaxPrice(Collection<Integer> categories);

    @Query(value = "SELECT min(price) FROM  articles WHERE category IN ?1", nativeQuery = true)
    int getMinPrice(Collection<Integer> categories);

    @Query(value = "SELECT * FROM articles WHERE category IN ?1 AND price >= ?2 AND price <= ?3", nativeQuery = true)
    Iterable<Article> getByFilter(Collection<Integer> categories, int minPrice, int maxPrice);
}
