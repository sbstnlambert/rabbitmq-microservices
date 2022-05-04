package be.technifutur.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvoiceService {

    private List<Invoice> invoices = new ArrayList<>();

    public void createInvoice(UUID bookingRef, int numberOfNightsBooked) {
        this.invoices.add(new Invoice(bookingRef, numberOfNightsBooked));
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }
}
