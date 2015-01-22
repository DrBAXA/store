package sombra.dao;

import java.util.Date;

public interface IUserDAO {
    void addUser(String name, String email,String password, Date registrationDate);
}
