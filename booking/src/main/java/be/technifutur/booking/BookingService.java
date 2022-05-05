package be.technifutur.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();
    private final MessageSender sender;
    private final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public BookingService(MessageSender sender) {
        this.sender = sender;
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

    public Booking getBookingByReference(UUID ref) {
        return bookings.stream()
                .filter(b -> b.getRef().equals(ref))
                .findFirst()
                .orElseThrow();
    }

    public void setToInvoiced(UUID ref) {
        this.bookings.stream()
                .filter(b -> b.getRef().equals(ref))
                .findFirst()
                .ifPresent(b -> b.setStatus(Booking.Status.INVOICED));
    }
}