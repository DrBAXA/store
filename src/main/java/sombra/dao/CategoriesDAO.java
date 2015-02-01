package sombra.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sombra.entity.Category;

public interface CategoriesDAO extends CrudRepository<Category, Integer>{
    @Query("FROM Category WHERE parentCategory=null")
    Iterable<Category> getRootCategories();

    Category getByName(String name);
}
