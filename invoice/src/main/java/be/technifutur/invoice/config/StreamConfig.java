package be.technifutur.invoice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
public class StreamConfig {

    @Bean
    public Function<String, String> processor() {
        log.info("JE SUIS ICI TU ME VOIS OU QUOI ?");
        return (in) -> {
            log.info("PROCESSING: " + in);
            return in + " PROCESSED";
        };
    }

}
