package com.ifood.demo.gatewayservice.http;

import com.ifood.demo.gatewayservice.models.Client;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "fullstack-demo-client-service")
@RibbonClient(name = "fullstack-demo-client-service")
@Headers("Content-Type: application/json")
public interface ClientHTTP {
    @GetMapping("/v1/clients/{id}")
    Client retrieveClient(@PathVariable("id") String id);

    @PostMapping("/v1/clients")
    Client createClient(@RequestBody Client client);
}
