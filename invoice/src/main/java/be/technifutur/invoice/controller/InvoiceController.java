package be.technifutur.invoice.controller;

import be.technifutur.invoice.model.Invoice;
import be.technifutur.invoice.service.InvoiceService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<Invoice> getInvoices() {
        return this.service.getInvoices();
    }

    @GetMapping("/price")
    public Double getBookingPrice(@RequestParam(name = "ref") UUID ref) {
        return this.service.getBookingPrice(ref);
    }

}
