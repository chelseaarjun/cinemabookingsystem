package com.gic.cinema.model;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents a movie ticket, encapsulating the screen and seat numbers of a reservation and providing functionality
 * to generate ticket identifiers.
 * <p>
 * The ticket identifier is generated using a fixed prefix "GIC" followed by a four-digit counter.
 * For example, if the counter is 1, the generated ticket ID will be "GIC0001".
 */
@Getter
@EqualsAndHashCode
@ToString
public class Ticket {
    private final String ticketId;
    private final Showtime showtime;
    private final Set<Seat> seats;

    Ticket(@NonNull String ticketId, @NonNull Showtime showtime, @NonNull Set<Seat> seats) {
        this.ticketId = ticketId;
        this.showtime = showtime;
        this.seats = seats;
    }

     /**
     * Generates a new ticket with selected seats.
     * <p>
     * Given ticketId is confirm for the showtime and the selected seats.
     * The ticket is then stored in the theater’s tickets map
     * </p>
     *
     * @param selectedSeats a set of seat identifiers (e.g., "A1", "B12") to be booked
     * @return the newly generated Ticket 
     */
    void confirmBooking() {
       this.showtime.reserveSeats(seats);
    }
}