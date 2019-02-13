package com.ifood.demo.gatewayservice.controller;

import com.ifood.demo.gatewayservice.models.Order;
import com.ifood.demo.gatewayservice.models.Page;
import com.ifood.demo.gatewayservice.models.Response;
import com.ifood.demo.gatewayservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientOrdersController.class)
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
@ActiveProfiles("test")
public class ClientOrdersControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retrieveOrders() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(
                "Peter",
                "peter@gmail.com.br",
                "21969031717",
                BigDecimal.valueOf(68.19999999999999),
                simpleDateFormat.parse("2019-02-13T19:58:43.025+0000"),
                UUID.fromString("51664d29-1c5a-46a1-971b-117f741b9682")
        ));
        when(orderService.retrieveClientOrders()).thenReturn(new Response<>(
                orders,
                new Page(1, 1, 1, 0)
        ));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/orders")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
//                .andExpect(content().json("\"{\"list\":[{\"name\":\"Peter\",\"email\":\"peter@gmail.com.br\",\"phone\":\"21969031717\",\"total\":68.19999999999999,\"confirmedAt\":\"2019-02-13T19:58:43.025+0000\",\"id\":\"51664d29-1c5a-46a1-971b-117f741b9682\"}],\"page\":{\"size\":1,\"totalElements\":1,\"totalPages\":1,\"number\":0}}\""))
                .andExpect(jsonPath("@.list[0].name").value("Peter"))
                .andExpect(jsonPath("@.list[0].email").value("peter@gmail.com.br"))
                .andExpect(jsonPath("@.page.size").value(1))
                .andExpect(jsonPath("@.page.totalElements").value(1))
                .andReturn();
    }
}