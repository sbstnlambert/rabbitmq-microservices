package be.technifutur.booking.service;

import be.technifutur.booking.communication.MessageSender;
import be.technifutur.booking.model.Booking;
import be.technifutur.booking.model.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();
    private final MessageSender sender;
    private final Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final RestTemplate template;

    public BookingService(MessageSender sender, RestTemplate template) {
        this.sender = sender;
        this.template = template;
    }

    public void createBooking(Booking booking) {
        try {
            sender.sendBookingToInvoice(booking);
            bookings.add(booking);
        } catch(Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Booking> getInvoicedBookings() {
        return bookings.stream()
                .filter(b -> b.getStatus() == Booking.Status.INVOICED)
                .toList();
    }

    public BookingDTO getBookingByReference(UUID ref) {
        Booking booking = bookings.stream()
                .filter(b -> b.getRef().equals(ref))
                .findFirst()
                .orElseThrow();
        ResponseEntity<Double> response = this.template.getForEntity(
                "http://localhost:8282/invoices/price?ref=" + ref,
                Double.class
        );
        Double price = response.getBody();
        return BookingDTO.builder()
                .ref(booking.getRef())
                .arrival(booking.getArrival())
                .departure(booking.getDeparture())
                .status(booking.getStatus())
                .price(price)
                .build();
    }

    public void setToInvoiced(UUID ref) {
        this.bookings.stream()
                .filter(b -> b.getRef().equals(ref))
                .findFirst()
                .ifPresent(b -> b.setStatus(Booking.Status.INVOICED));
    }
}