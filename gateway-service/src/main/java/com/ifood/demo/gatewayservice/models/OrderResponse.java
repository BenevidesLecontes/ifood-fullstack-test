package com.ifood.demo.gatewayservice.models;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private Embedded _embedded;
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static class Embedded {
        public List<ClientOrder> orders = new ArrayList<>();

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
