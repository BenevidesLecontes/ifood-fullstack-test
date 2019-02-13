package com.ifood.demo.gatewayservice.http;

import com.ifood.demo.gatewayservice.models.ClientOrder;
import com.ifood.demo.gatewayservice.models.OrderResponse;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@FeignClient(name = "fullstack-demo-order-service")
@RibbonClient(name = "fullstack-demo-order-service")
@Headers("Content-Type: application/json")
public interface OrderHTTP {
    @GetMapping("/v1/orders")
    OrderResponse retrieveOrders();

    @PostMapping("/v1/orders")
    ClientOrder createClientOrder(@RequestBody ClientOrder clientOrder);

    @GetMapping("/v1/orders/{id}")
    ClientOrder retrieveOrder(@PathVariable("id") String id);

    @GetMapping("/v1/orders/search/byClientId")
    OrderResponse retrieveOrderByClientId(@RequestParam("clientId") String clientId);

    @GetMapping("/v1/orders/search/byDate")
    OrderResponse retrieveOrderByDate(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);

    @GetMapping("/v1/orders/search/byClientAndDate")
    OrderResponse retrieveOrderByClientIdAndDate(
            @RequestParam("clientId") String clientId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end);
}
