package be.technifutur.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class MessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    private final InvoiceService service;
    private final ObjectMapper mapper;

    public MessageReceiver(InvoiceService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = "booking_queue")
    public void receive(String message) throws JsonProcessingException {
        Booking booking = mapper.readValue(message, Booking.class);
        logger.info("BOOKING RECEIVED - " + booking);
        this.service.createInvoice(
                booking.getRef(),
                (int)ChronoUnit.DAYS.between(booking.getArrival(), booking.getDeparture())*10
        );
    }
}