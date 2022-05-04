package be.technifutur.apiGateway.booking;

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