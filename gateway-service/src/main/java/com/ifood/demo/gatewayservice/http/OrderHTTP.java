package com.ifood.demo.gatewayservice.http;

import com.ifood.demo.gatewayservice.models.ClientOrder;
import com.ifood.demo.gatewayservice.models.OrderResponse;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "fullstack-demo-order-service")
@RibbonClient(name = "fullstack-demo-order-service")
@Headers("Content-Type: application/json")
public interface OrderHTTP {
    @GetMapping("/v1/orders")
    OrderResponse retrieveOrders();

    @PostMapping("/v1/orders")
    ClientOrder createClientOrder(@RequestBody ClientOrder clientOrder);
}
