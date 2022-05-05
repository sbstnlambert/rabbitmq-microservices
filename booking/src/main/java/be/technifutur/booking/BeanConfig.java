package be.technifutur.booking;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    public ObjectMapper mapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

}
