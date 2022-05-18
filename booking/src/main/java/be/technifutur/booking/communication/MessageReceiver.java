package be.technifutur.booking.communication;

import be.technifutur.booking.service.BookingService;
import be.technifutur.booking.model.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private final BookingService service;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    public MessageReceiver(BookingService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = "invoice_queue")
    public void receiveBooking(String message) throws JsonProcessingException {
        logger.info("INVOICE RECEIVED - " + message);
        Invoice invoice = mapper.readValue(message, Invoice.class);
        service.setToInvoiced(invoice.getBookingRef());
        service.getInvoicedBookings().forEach(System.out::println);
    }
}
