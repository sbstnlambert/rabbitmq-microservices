package be.technifutur.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private final List<Invoice> invoices = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final MessageSender sender;

    public InvoiceService(MessageSender sender) {
        this.sender = sender;
    }

    public void createInvoice(UUID bookingRef, int numberOfNightsBooked) {
        Invoice i = new Invoice(bookingRef, numberOfNightsBooked*10);
        try {
            sender.sendInvoiceToBooking(i);
            this.invoices.add(i);
        } catch(JsonProcessingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Invoice> getInvoices() {
        return new ArrayList<>(this.invoices);
    }

    public Double getBookingPrice(UUID ref) {
        return this.invoices.stream()
                .filter(i -> i.getBookingRef().equals(ref))
                .findFirst()
                .map(Invoice::getPrice)
                .orElse(null);
    }
}
