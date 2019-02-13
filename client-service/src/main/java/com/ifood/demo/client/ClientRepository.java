package com.ifood.demo.client;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.UUID;


public interface ClientRepository extends PagingAndSortingRepository<Client, UUID> {

	@RestResource(path = "byName")
	Collection<Client> findByNameIgnoreCaseContaining(@Param("name") String name);
	
	@RestResource(path = "byPhone")
	Collection<Client> findByPhoneIgnoreCaseContaining(@Param("phone") String phone);
	
	@RestResource(path = "byEmail")
	Collection<Client> findByEmailIgnoreCaseContaining(@Param("email") String email);

	@RestResource(path = "allBy")
	Collection<Client> findByEmailIgnoreCaseOrPhoneIgnoreCaseOrNameIgnoreCase(
			@Param("email") String email,
			@Param("phone") String phone,
			@Param("name") String name);
}