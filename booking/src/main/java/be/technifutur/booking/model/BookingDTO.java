package be.technifutur.booking.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class BookingDTO {

    private UUID ref;
    private LocalDate arrival;
    private LocalDate departure;
    private Booking.Status status;
    private Double price;

}
