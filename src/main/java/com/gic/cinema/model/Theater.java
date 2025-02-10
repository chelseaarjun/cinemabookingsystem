package com.gic.cinema.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@ToString
public class Theater {
    private final static String TICKET_PREFIX = "GIC"; 
    private final Screen screen;
    private final Showtime showtime;
    private final Map<String, Ticket> tickets;

    public Theater(@NonNull String movieName, int totalRows, int seatsPerRow) {
        this.screen = new DefaultScreen(totalRows, seatsPerRow);
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
     * Retrieves a booked ticket by its ticket ID.
     * <p>
     * Note: This implementation uses {@code Optional.of} on the value found in the tickets map.
     * </p>
     *
     * @param ticketId the identifier of the ticket
     * @return an Optional containing the Ticket if found, otherwise an empty Optional
     */
    public Ticket generateTicket(Set<Seat> selectedSeats) {
        return new Ticket(generateTicketID(), showtime, selectedSeats);
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
    public void confirmTicket(Ticket ticket) {
        ticket.confirmBooking();
        tickets.put(ticket.getTicketId(), ticket);
    }

    /**
     * Generates a new ticket ID.
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
    String generateTicketID() {
        int ticketCounter = tickets.size() + 1;
        if (ticketCounter > 9999) {
            throw new IllegalStateException("Ticket counter is not expected to be more than 4 digits!!!");
        }
        return String.format("%s%04d", TICKET_PREFIX, ticketCounter);
    }
}
