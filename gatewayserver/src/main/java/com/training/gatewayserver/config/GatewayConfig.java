package com.training.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/bt/employee-attendance/**")
                        .filters(f -> f.rewritePath("/bt/employee-attendance/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://EMPLOYEE-ATTENDANCE"))
                .route(p -> p
                        .path("/bt/attendance-store/**")
                        .filters(f -> f.rewritePath("/bt/attendance-store/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ATTENDANCE-STORE"))
                .build();

    }


}
