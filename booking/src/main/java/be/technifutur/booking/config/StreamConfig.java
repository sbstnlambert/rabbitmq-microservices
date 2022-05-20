package be.technifutur.booking.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@Slf4j
public class StreamConfig {

    @Bean
    public Consumer<String> input() {
        return (message) -> log.info("RECEIVED: " + message);
    }

}
