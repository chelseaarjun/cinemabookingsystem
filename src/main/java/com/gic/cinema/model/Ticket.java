package com.gic.cinema.model;

import java.util.List;

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
@ToString
public class Ticket {
    private final static String TICKET_PREFIX = "GIC"; 
    private final String ticketId;
    private final Showtime showtimes;
    private final List<String> seatNumbers;

    private Ticket(@NonNull Showtime showtimes, @NonNull List<String> seatNumbers, int ticketNumber) {
        this.ticketId = generateTicketID(ticketNumber);
        this.showtimes = showtimes;
        this.seatNumbers = seatNumbers;
    }

    /**
     * Generate ticket IDs of the format GIC0001, GIC0002 etc.
     * @param counter: 
     * @return 
     */
    static String generateTicketID(final int counter){
        if (counter > 9999) {
            throw new IllegalStateException("Ticket counter is not expected to be more than 4 digits!!!");
        }
        return String.format("%s%04d", TICKET_PREFIX, counter);
    }

    public static Ticket createTicket(Showtime showtimes, List<String> seatNumbers, int ticketNumber) {
        return new Ticket(showtimes, seatNumbers, ticketNumber);
    }   
}