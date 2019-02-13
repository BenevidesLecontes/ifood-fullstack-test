## What's implemented
Service discovery with Feign
Naming Server with Eureka
LoadBalancing with Ribbon and Eureka
Basic Fault tolerance with Hystrix (I used it only in one method)

## What could be implemented
1 - A API Gateway for Authentication, Authorization and security,
Rate Limits, fault toleration and service aggregation

2 - Distributed tracing

3 - Spring cloud bus

4 - Documentation

5 - Pagination on the frontEnd side


## What 


## To run the backend project
Run these with mvn package and mvn spring-boot:run
in this specific order
1 - Run the naming-server service;
2 - Run the Client and Order micro services;
3 - Run the gateway service;

## To run the frontend project
1 - Go to order-web folder and install the dependencies with yarn or npm install 
2 - Run it with yarn start or npm run start,
it will run the project with a proxy to the api