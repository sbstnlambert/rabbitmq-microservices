package be.technifutur.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {

    private UUID ref;
    private Date arrival; // Arrive at 12:00
    private Date departure; // Leave at 12:00
    private String status;

}
