package com.ifood.demo.gatewayservice.service;

import com.ifood.demo.gatewayservice.http.ClientHTTP;
import com.ifood.demo.gatewayservice.http.OrderHTTP;
import com.ifood.demo.gatewayservice.models.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderHTTP orderHTTP;
    private final ClientHTTP clientHTTP;

    public OrderService(OrderHTTP orderHTTP, ClientHTTP clientHTTP) {
        this.orderHTTP = orderHTTP;
        this.clientHTTP = clientHTTP;
    }

    @HystrixCommand(fallbackMethod = "retrieveClientOrdersFallback")
    public Response<Order> retrieveClientOrders() {
        OrderResponse orderResponse = orderHTTP.retrieveOrders();
        return new Response<>(buildOrders(orderResponse.getEmbedded().orders), orderResponse.getPage());
    }

    @HystrixCommand(fallbackMethod = "retrieveOrderItemsFallback")
    public Collection<ClientOrder.Item> retrieveOrderItems(String id) {
        ClientOrder orderResponse = orderHTTP.retrieveOrder(id);

        return orderResponse.getItems();
    }

    @HystrixCommand(fallbackMethod = "searchFallback")
    public Response<Order> search(Date start, Date end, String name, String phone, String email) {
        final OrderResponse orderResponse = new OrderResponse();
        if (start != null && end != null && (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(phone) || StringUtils.isNotEmpty(email))) {
            ClientResponse searchResult = clientHTTP.search(name, phone, email);
            searchResult.getEmbedded().clients.forEach(client -> {
                OrderResponse result = orderHTTP.retrieveOrderByClientIdAndDate(client.getId().toString(), start, end);
                orderResponse.set_embedded(result.getEmbedded());
                orderResponse.setPage(result.getPage());
            });
        } else if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(phone) || StringUtils.isNotEmpty(email)) {
            ClientResponse searchResult = clientHTTP.search(name, phone, email);
            searchResult.getEmbedded().clients.forEach(client -> {
                OrderResponse result = orderHTTP.retrieveOrderByClientId(client.getId().toString());
                orderResponse.setPage(result.getPage());
                orderResponse.set_embedded(result.getEmbedded());
            });
        } else if (start != null && end != null) {
            OrderResponse result = orderHTTP.retrieveOrderByDate(start, end);
            orderResponse.set_embedded(result.getEmbedded());
            orderResponse.setPage(result.getPage());
        }
        return new Response<>(buildOrders(orderResponse.getEmbedded().orders), orderResponse.getPage());
    }

    private List<Order> buildOrders(List<ClientOrder> clientOrders) {
        List<Order> orders = new ArrayList<>();
        clientOrders.forEach(clientOrder -> {
            Client client = clientHTTP.retrieveClient(clientOrder.getClientId().toString());
            orders.add(new Order(client.getName(),
                    client.getEmail(), client.getPhone(),
                    clientOrder.getTotal(), clientOrder.getConfirmedAt(), clientOrder.getId()));
        });

        return orders;
    }


    public Response<Order> searchFallback(Date start, Date end, String name, String phone, String email) {
        return new Response<>(new ArrayList<>(), new Page());
    }

    public Response<Order> retrieveClientOrdersFallback() {
        return new Response<>(new ArrayList<>(), new Page());
    }
    public Collection<ClientOrder.Item> retrieveOrderItemsFallback(String id) {
        return new ArrayList<>();
    }
}
