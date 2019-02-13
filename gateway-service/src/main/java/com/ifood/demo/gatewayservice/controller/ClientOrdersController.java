package com.ifood.demo.gatewayservice.controller;

import com.ifood.demo.gatewayservice.models.ClientOrder;
import com.ifood.demo.gatewayservice.models.Order;
import com.ifood.demo.gatewayservice.models.Response;
import com.ifood.demo.gatewayservice.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@RestController
public class ClientOrdersController {
    private OrderService orderService;

    public ClientOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    Response<Order> retrieveOrders() {
        return orderService.retrieveClientOrders();
    }

    @GetMapping("/orders/{id}")
    Collection<ClientOrder.Item> retrieveOrderItems(@PathVariable String id) {
        return orderService.retrieveOrderItems(id);
    }

    @GetMapping("/orders/search")
    Response<Order> search(@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                             @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "email", required = false) String email) {
        return orderService.search(start, end, name, phone, email);
    }
}
