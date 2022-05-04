package be.technifutur.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public void createBooking(Booking booking) {

//        try {
//            sender.sendBookingToFacture(booking);
//            bookings.add(booking);
//        } catch(Exception e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//        }

        this.bookings.add(booking);
    }

    public List<Booking> getInvoicedBookings() {
        return this.bookings;
    }

    public void setToInvoiced(UUID ref) {
        this.bookings.stream()
                .filter(b -> b.getRef() == ref)
                .findFirst()
                .ifPresent(b -> b.setStatus(Booking.Status.INVOICED));
    }
}