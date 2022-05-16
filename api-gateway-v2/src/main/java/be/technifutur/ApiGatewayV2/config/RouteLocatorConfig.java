package be.technifutur.ApiGatewayV2.config;

import be.technifutur.ApiGatewayV2.predicate.NumberOfParamsRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    public RouteLocator routeLocator(RouteLocatorBuilder builder, NumberOfParamsRoutePredicateFactory predicateFactory) {
        return builder.routes()
                .route("to-booking",
                        r -> r.path("/bookings")
                                .and()
                                .method("POST")
                                .uri("lb://booking-service")
                )
                .route("to-booking-invoiced",
                        r -> r.path("/bookings/invoiced")
                                .and()
                                .method("GET")
                                .uri("lb://booking-service")
                )
                .build();
    }

}
