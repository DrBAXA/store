package sombra.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sombra.entity.User;


public interface UsersDAO extends CrudRepository<User, Integer> {

    @Query("FROM User WHERE name=?")
    User findOne(String name);

    Long countByName(String name);

    Long countByEmail(String name);

    Long countByPhone(String name);

}
