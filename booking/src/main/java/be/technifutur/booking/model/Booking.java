package be.technifutur.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
public class Booking {

    private UUID ref = UUID.randomUUID();
    private LocalDate arrival; // Arrivée à 12:00
    private LocalDate departure; // Départ à 12:00
    private Status status = Status.REQUESTED;

    public Booking(LocalDate arrival, LocalDate departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    public static enum Status {
        REQUESTED,
        INVOICED
    }

}