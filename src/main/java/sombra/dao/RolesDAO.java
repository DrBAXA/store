package sombra.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sombra.entity.Role;

public interface RolesDAO extends CrudRepository<Role, Integer> {

    @Query("FROM Role WHERE name=?")
    Role findOne(String name);
}
