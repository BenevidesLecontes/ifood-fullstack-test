package com.ifood.demo.gatewayservice.http;

import com.ifood.demo.gatewayservice.models.Client;
import com.ifood.demo.gatewayservice.models.ClientResponse;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "fullstack-demo-client-service")
@RibbonClient(name = "fullstack-demo-client-service")
@Headers("Content-Type: application/json")
public interface ClientHTTP {
    @GetMapping("/v1/clients/{id}")
    Client retrieveClient(@PathVariable("id") String id);

    @PostMapping("/v1/clients")
    Client createClient(@RequestBody Client client);

    @GetMapping("/v1/clients/search/allBy")
    ClientResponse search(@RequestParam("name") String name, @RequestParam("phone") String phone,
                          @RequestParam("email") String email);
}
