package com.ifood.demo.gatewayservice.models;

import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String email;
    private String phone;

    public Client() {
    }

    public Client(UUID id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "Client (id=%s, name=%s, email=%s, phone=%s)", this.id, this.name, this.email, this.phone);
    }
}
