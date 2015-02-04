package sombra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sombra.dao.OrdersDAO;
import sombra.entity.Order;

@Service
public class OrderService {

    @Autowired
    OrdersDAO ordersDAO;

    public PageImpl<Order> getAll(Pageable pageable){
        return (PageImpl<Order>)ordersDAO.findAll(pageable);
    }
}
