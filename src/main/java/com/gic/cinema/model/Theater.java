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
    
    /**
     * Retrieves a booked ticket by its ticket ID.
     * <p>
     * Note: This implementation uses {@code Optional.of} on the value found in the tickets map.
     * </p>
     *
     * @param ticketId the identifier of the ticket
     * @return an Optional containing the Ticket if found, otherwise an empty Optional
     */
    public Optional<Ticket> getBookedTicket(@NonNull String ticketId) {
        if(tickets.containsKey(ticketId)) {
            return Optional.of(tickets.get(ticketId));
        }
        return Optional.empty();
    }

    /**
     * Generates a new ticket with selected seats.
     * <p>
     * A new Ticket is created using the current showtime, the selected seats,
     * and a ticket number derived from the number of tickets already issued.
     * The ticket is then stored in the theater’s tickets map and the selected
     * seats are reserved via the showtime.
     * </p>
     *
     * @param selectedSeats a set of seat identifiers (e.g., "A1", "B12") to be booked
     * @return the newly generated Ticket 
     */
    public Ticket generateTicket(Set<String> selectedSeats) {
        Ticket newTicket = new Ticket(showtime, selectedSeats, tickets.size() + 1);
        tickets.put(newTicket.getTicketId(), newTicket);
        showtime.reserveSeats(selectedSeats);
        return newTicket;
    }
}
