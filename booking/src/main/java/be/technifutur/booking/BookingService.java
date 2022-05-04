package be.technifutur.apiGateway.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .filter(b -> b.getStatus().equals(Booking.Status.INVOICED))
                .toList();
    }

    public void setToInvoiced(UUID ref) {
        this.bookings.stream()
                .filter(b -> b.getRef() == ref)
                .findFirst()
                .ifPresent(b -> b.setStatus(Booking.Status.INVOICED));
    }
}