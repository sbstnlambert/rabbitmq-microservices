package be.technifutur.apiGateway.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {

    private UUID bookingRef;
    private double price;

}
