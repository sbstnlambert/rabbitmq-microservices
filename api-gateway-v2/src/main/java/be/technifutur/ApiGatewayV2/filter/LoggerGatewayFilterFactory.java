package be.technifutur.ApiGatewayV2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

// Not used
@Slf4j
@Component
public class LoggerGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggerGatewayFilterFactory.Config> {

    public LoggerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info(exchange.getRequest().getMethod() + " - " + exchange.getRequest().getURI());
            return chain.filter(exchange);
        });
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    public static class Config {
    }

}