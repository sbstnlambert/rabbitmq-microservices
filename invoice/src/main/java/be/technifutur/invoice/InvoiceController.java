package be.technifutur.invoice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Invoice> getInvoices() {
        return this.service.getInvoices();
    }

    @GetMapping("/price")
    public Double getBookingPrice(@RequestParam(name = "ref") UUID ref) {
        return this.service.getBookingPrice(ref);
    }

}
