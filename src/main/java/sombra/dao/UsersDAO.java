package sombra.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sombra.entity.User;

@Repository
@Qualifier("UsersDAO")
public interface UsersDAO extends CrudRepository<User, Integer>{

    @Query("FROM User WHERE name=?")
    public User findOne(String name);

}
