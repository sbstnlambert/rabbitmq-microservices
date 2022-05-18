package be.technifutur.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {

    private UUID ref;
    private LocalDate arrival; // Arrive at 12:00
    private LocalDate departure; // Leave at 12:00
    private String status;

}
