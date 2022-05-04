package be.technifutur.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageSender implements InitializingBean {

    private final RabbitTemplate rabbitTemplate;
    private final  ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void sendBookingToInvoice(Booking booking) throws JsonProcessingException {
        String bJson = mapper.writeValueAsString(booking);
        Message m = MessageBuilder.withBody(bJson.getBytes())
                        .setContentType("application/json")
                                .build();
        logger.info("SENDING booking TO invoice: " + m);
        rabbitTemplate.convertAndSend("direct.exchange", "booking_key", m);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        sendBookingToInvoice(
                new Booking(
                        UUID.randomUUID(),
                        new Date(),
                        new Date(),
                        Booking.Status.REQUESTED
                )
        );
        // TODO: Reprendre la correction ici
    }
}
