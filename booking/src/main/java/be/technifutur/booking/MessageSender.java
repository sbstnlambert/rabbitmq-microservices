package be.technifutur.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageSender implements InitializingBean {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBookingToInvoice(String json) {
        rabbitTemplate.convertAndSend("direct.exchange", "booking_key", json);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Booking booking = new Booking(
                UUID.randomUUID(),
                new Date(),
                new Date(),
                Booking.Status.REQUESTED
        );
        ObjectMapper mapper = new ObjectMapper();
        String bJson = mapper.writeValueAsString(booking);
        sendBookingToInvoice(bJson);
    }
}
