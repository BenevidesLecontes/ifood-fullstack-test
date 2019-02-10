package com.ifood.demo.gatewayservice.models;

import java.util.List;

public class OrderResponse {
    private Embedded _embedded;

    public static class Embedded {
        public List<ClientOrder> orders;

        public List<ClientOrder> getOrders() {
            return orders;
        }

        public void setOrders(List<ClientOrder> orders) {
            this.orders = orders;
        }
    }
    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Embedded getEmbedded() {
        return _embedded;
    }
}
