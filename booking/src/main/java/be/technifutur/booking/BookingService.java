package be.technifutur.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public void createBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public List<Booking> getInvoicedBookings() {
        return this.bookings;
    }

    public void setToInvoiced(UUID ref) {
        Booking booking = this.bookings.stream()
                .filter(b -> b.getRef() == ref)
                .findFirst()
                .orElseThrow();
        booking.setStatus(Booking.Status.INVOICED);
    }
}