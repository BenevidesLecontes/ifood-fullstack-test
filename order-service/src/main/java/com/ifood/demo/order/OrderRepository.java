package com.ifood.demo.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public interface OrderRepository extends PagingAndSortingRepository<Order, UUID> {

    @RestResource(path = "byClientId")
    Collection<Order> findByClientId(@Param("clientId") UUID clientId);

    @RestResource(path = "byRestaurantId")
    Collection<Order> findByRestaurantId(@Param("restaurantId") UUID restaurantId);

    @RestResource(path = "byDate")
    Page<Order> findByCreatedAtBetween(
            @Param("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @Param("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, Pageable pageable);

    @RestResource(path = "byClientAndDate")
    Page<Order> findByClientIdAndCreatedAtBetween(
            @Param("clientId") UUID clientId,
            @Param("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @Param("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, Pageable pageable);
}