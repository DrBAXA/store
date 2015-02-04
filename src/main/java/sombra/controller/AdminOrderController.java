package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sombra.entity.Order;
import sombra.service.OrderService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrders(ModelMap modelMap, Pageable pageable){
        modelMap.addAttribute("disabled", "orders");

        PageImpl<Order> orderList = orderService.getAll(pageable);
        modelMap.addAttribute("orderList", orderList);
        modelMap.addAttribute("totalPages", orderList.getTotalPages());

        if(pageable.getSort() != null){
            modelMap.addAttribute("sort", pageable.getSort().toString().split(":")[0]);
        }
        modelMap.addAttribute("currentPage", pageable.getPageNumber());
        modelMap.addAttribute("pageSize", pageable.getPageSize());

        return "adminOrders";
    }
}
