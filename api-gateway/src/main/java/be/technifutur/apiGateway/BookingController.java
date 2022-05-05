package be.technifutur.apiGateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final RestTemplate template;
    private final String BASE_URL = "http://localhost:8181/bookings";

    public BookingController(RestTemplate template) {
        this.template = template;
    }

    @PostMapping
    public ResponseEntity<?> bookingRequest(@RequestBody Map<String, String> request) {
        System.out.println(request);
        ResponseEntity<Object> response = template.postForEntity(this.BASE_URL, request, Object.class);
        return response;
    }

    @GetMapping("/invoiced")
    public ResponseEntity<?> getInvoicedBookings() {
        return template.getForEntity(this.BASE_URL + "/invoiced", Object.class);
    }
}
