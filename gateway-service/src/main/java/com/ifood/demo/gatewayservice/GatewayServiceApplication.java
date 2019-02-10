package com.ifood.demo.gatewayservice;

import com.ifood.demo.gatewayservice.http.ClientHTTP;
import com.ifood.demo.gatewayservice.http.OrderHTTP;
import com.ifood.demo.gatewayservice.models.Client;
import com.ifood.demo.gatewayservice.models.ClientOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(ClientHTTP clientHTTP, OrderHTTP orderHTTP) {
        return args -> Stream.of("peter", "lois", "stewie", "brian")
                .map(name -> new Client(UUID.randomUUID(), name, name + "@gmail.com.br", new Random().nextLong() + ""))
                .map(c -> {
                    Client client = clientHTTP.createClient(c);
                    return client.getId();
                })
                .map(clientId -> createOrderForClient(orderHTTP, clientId))
                .forEach(System.out::println);
    }

    private List<ClientOrder> createOrderForClient(OrderHTTP orderHTTP, UUID clientId) {
        List<ClientOrder> clientOrders = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(6); i++) {
            clientOrders.add(orderHTTP.createClientOrder(new ClientOrder(UUID.randomUUID(), clientId, UUID.randomUUID(), new Date(), new Date(), buildItems())));
        }

        return clientOrders;
    }

    private List<ClientOrder.Item> buildItems() {
        Random random = new Random();
        return Stream.of("lorem ipsum", "mate", "mate leão")
                .map(d -> new ClientOrder.Item(d, random.nextInt(20), generatePrice()))
                .collect(Collectors.toList());

    }

    private double generatePrice() {
        double min = 0;
        double max = 2.10;
        double diff = max - min;
        DecimalFormat formatter = new DecimalFormat("#0.00");  // edited here.
        double randomValue = min + Math.random( ) * diff;
        double tempRes = Math.floor(randomValue * 10);
        double finalRes = tempRes / 10;
        return Double.valueOf(formatter.format(finalRes));
    }
}

