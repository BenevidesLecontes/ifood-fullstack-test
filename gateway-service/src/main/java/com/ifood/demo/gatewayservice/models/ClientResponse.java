package com.ifood.demo.gatewayservice.models;

import java.util.List;

public class ClientResponse {
    private Embedded _embedded;
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Embedded getEmbedded() {
        return _embedded;
    }

    public static class Embedded {
        public List<Client> clients;

        public List<Client> setClients() {
            return clients;
        }

        public void setClients(List<Client> clients) {
            this.clients = clients;
        }
    }
}
