package com.ifood.demo.gatewayservice.service;

import com.ifood.demo.gatewayservice.models.Client;
import com.ifood.demo.gatewayservice.models.Order;
import com.ifood.demo.gatewayservice.models.OrderResponse;
import com.ifood.demo.gatewayservice.http.ClientHTTP;
import com.ifood.demo.gatewayservice.http.OrderHTTP;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderHTTP orderHTTP;
    private final ClientHTTP clientHTTP;

    public OrderService(OrderHTTP orderHTTP, ClientHTTP clientHTTP) {
        this.orderHTTP = orderHTTP;
        this.clientHTTP = clientHTTP;
    }

    public Collection<Order> retrieveClientOrders() {
        OrderResponse orderResponse = orderHTTP.retrieveOrders();
        Collection<Order> orders = new ArrayList<>();
        orderResponse.getEmbedded().orders.forEach(clientOrder -> {
            BigDecimal total = BigDecimal.ZERO;
            if (clientOrder.getItems() != null) {
                Optional<Double> reduce = clientOrder.getItems().stream().map(o -> o.getQuantity() * o.getPrice())
                        .reduce((acc, value) -> acc + value);
                if (reduce.isPresent()) {
                    total = BigDecimal.valueOf(reduce.get());
                }
            }
            Client client = clientHTTP.retrieveClient(clientOrder.getClientId().toString());
            orders.add(new Order(client.getName(), client.getEmail(),
                    client.getPhone(), total, clientOrder.getConfirmedAt(), clientOrder.getId()));
        });

        return orders;
    }
}
