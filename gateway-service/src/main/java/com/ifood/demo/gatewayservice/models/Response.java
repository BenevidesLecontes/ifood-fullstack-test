package com.ifood.demo.gatewayservice.models;

import java.util.List;

public class Response<T> {
    private List<T> list;
    private Page page;

    public Response(List<T> list, Page page) {
        this.list = list;
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
