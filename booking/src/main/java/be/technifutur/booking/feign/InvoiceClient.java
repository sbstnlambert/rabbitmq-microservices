package be.technifutur.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {

    @GetMapping(path = "/invoices/price", params = "ref")
    Double getBookingPrice(@RequestParam(name = "ref") UUID ref);

}
