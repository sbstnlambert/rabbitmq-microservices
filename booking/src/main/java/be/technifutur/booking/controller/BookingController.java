package be.technifutur.booking.controller;

import be.technifutur.booking.model.BookingForm;
import be.technifutur.booking.service.BookingService;
import be.technifutur.booking.model.Booking;
import be.technifutur.booking.model.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;
    private final StreamBridge streamBridge;

    public BookingController(BookingService service, StreamBridge streamBridge) {
        this.service = service;
        this.streamBridge = streamBridge;
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

    // STREAM
    @GetMapping("/test")
    public void send() {
        log.info("Coucou Greg! Ca va ? A la cool ? On se met bien ? Apéro ?");
        this.streamBridge.send("output-out-0", "Ah que coucou ! Je stream.");
    }

}
