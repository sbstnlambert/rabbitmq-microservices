package be.technifutur.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void bookingRequest(@RequestBody BookingForm form) {
        this.service.createBooking(form.map());
    }

    @GetMapping("/invoiced")
    public List<Booking> getInvoicedBookings() {
        return this.service.getInvoicedBookings();
    }
}
