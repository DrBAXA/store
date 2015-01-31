package sombra.dao;


import org.springframework.data.repository.CrudRepository;
import sombra.entity.Order;

public interface OrdersDAO extends CrudRepository<Order, Integer>{
}
