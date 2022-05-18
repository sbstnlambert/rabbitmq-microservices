package be.technifutur.booking.controller;

import be.technifutur.booking.service.BookingService;
import be.technifutur.booking.model.Booking;
import be.technifutur.booking.model.BookingDTO;
import be.technifutur.booking.model.BookingForm;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/invoiced")
    public List<Booking> getInvoicedBookings() {
        return this.service.getInvoicedBookings();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/booking")
    public BookingDTO getBookingByReference(@RequestParam(name = "ref") UUID ref) {
        return this.service.getBookingByReference(ref);
    }

}
