package com.ifood.demo.gatewayservice.controller;

import com.ifood.demo.gatewayservice.models.Order;
import com.ifood.demo.gatewayservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/orders")
public class ClientOrdersController {
    private OrderService orderService;

    public ClientOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    Collection<Order> retrieveOrders() {
        return orderService.retrieveClientOrders();
    }
}
