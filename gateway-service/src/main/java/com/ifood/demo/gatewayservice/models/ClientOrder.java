package com.ifood.demo.gatewayservice.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ClientOrder {
    private UUID id;
    private UUID clientId;
    private UUID restaurantId;
    private Date createdAt;
    private Date confirmedAt;


    private List<Item> items;

    public ClientOrder() {
    }

    public ClientOrder(UUID id, UUID clientId, UUID restaurantId, Date createdAt, Date confirmedAt, List<Item> items) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.createdAt = createdAt;
        this.confirmedAt = confirmedAt;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public static class Item {
        private Integer quantity;
        private Double price;
        private String description;

        public Item() {
        }

        public Item(String description, int quantity, double price) {
            this.description = description;
            this.quantity = quantity;
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
