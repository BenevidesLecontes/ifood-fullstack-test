package com.ifood.demo.gatewayservice.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Order {
    private String name;
    private String email;
    private String phone;
    private BigDecimal total;
    private Date confirmedAt;
    private UUID id;

    public Order(String name, String email, String phone, BigDecimal total, Date confirmedAt, UUID id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.total = total;
        this.confirmedAt = confirmedAt;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
