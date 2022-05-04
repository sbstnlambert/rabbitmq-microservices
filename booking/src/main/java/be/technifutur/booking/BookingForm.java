package be.technifutur.booking;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingForm {
    private LocalDate arrival;
    private LocalDate departure;

    public Booking map() {
        return new Booking(arrival, departure);
    }
}
