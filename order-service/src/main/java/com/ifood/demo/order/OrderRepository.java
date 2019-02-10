package com.ifood.demo.order;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public interface OrderRepository extends PagingAndSortingRepository<Order, UUID> {

	@RestResource(path = "byClientId")
	Collection<Order> findByClientId(@Param("clientId") UUID clientId);
	
	@RestResource(path = "byRestaurantId")
	Collection<Order> findByRestaurantId(@Param("restaurantId") UUID restaurantId);
	
	@RestResource(path = "byDate")
	Collection<Order> findByCreatedAtBetween(@Param("start") Date start, @Param("end") Date end);
}