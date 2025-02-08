package com.gic.cinema.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents a cinema theater for a specific movie.
 * <p>
 * The Theater class encapsulates the seating arrangement and ticket management
 * for a movie screening. It creates a list of seats based on the provided number
 * of rows and seats per row. For bookings, it maintains a mapping of ticket IDs to
 * Ticket objects.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 *   <li>Maximum rows: 26 (rows are labeled A-Z)</li>
 *   <li>Maximum seats per row: 50</li>
 *   <li>The movie name must not be null.</li>
 * </ul>
 * </p>
 */
@Getter
@ToString
public class Theater {
    private final Screen screen;
    private final Showtime showtime;
    private final Map<String, Ticket> tickets;

    public Theater(@NonNull String movieName, int rows, int seatsPerRow) {
        this.screen = new Screen(rows, seatsPerRow);
        this.showtime = new Showtime(movieName, screen);
        this.tickets = new HashMap<>();
    }
    
    public Optional<Ticket> getBookedTicket(String ticketId) {
        return Optional.of(tickets.get(ticketId));
    }

    public Ticket generateTicket(Set<String> selectedSeats) {
        Ticket newTicket = new Ticket(showtime, selectedSeats, tickets.size() + 1);
        tickets.put(newTicket.getTicketId(), newTicket);
        showtime.reserveSeats(selectedSeats);
        return newTicket;
    }
}
