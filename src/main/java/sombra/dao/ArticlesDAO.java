package sombra.dao;

import org.springframework.data.repository.CrudRepository;
import sombra.entity.Article;

public interface ArticlesDAO extends CrudRepository<Article, Integer>{
}
