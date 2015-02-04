package sombra.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import sombra.entity.Order;

public interface OrdersDAO extends PagingAndSortingRepository<Order, Integer> {

}
