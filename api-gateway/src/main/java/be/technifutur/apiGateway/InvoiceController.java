package be.technifutur.apiGateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final RestTemplate template;
    private final String BASE_URL = "http://localhost:8282/invoices";

    public InvoiceController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping
    public ResponseEntity<?> getInvoices() {
        return this.template.getForEntity(this.BASE_URL, Object.class);
    }
}
