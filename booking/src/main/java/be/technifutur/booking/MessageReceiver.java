package be.technifutur.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitListener(queues = "invoice_queue")
    public void receive(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Invoice invoice = mapper.readValue(message, Invoice.class);
        logger.info("INVOICE RECEIVED - " + invoice);
    }
}
