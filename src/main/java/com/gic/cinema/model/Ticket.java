package com.gic.cinema.model;

import java.util.Set;
import java.util.stream.Collectors;

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
    private final static String TICKET_PREFIX = "GIC"; 
    private final String ticketId;
    private final Showtime showtime;
    private final Set<Seat> reservedSeatIds;

    Ticket(@NonNull Showtime showtime, @NonNull Set<Seat> reservedSeatIds, int ticketCounter) {
        this.ticketId = generateTicketID(ticketCounter);
        this.showtime = showtime;
        this.reservedSeatIds = reservedSeatIds;
        checkSeatsReserved(reservedSeatIds);
    }

    /**
     * Check all seats are reserved.
     * @param counter:
     * @throws IIlegalStateException if any of the seats are not reserved
     * @return 
     */
    private void checkSeatsReserved(Set<Seat> reservedSeats){
        int unreservedTicketCount = reservedSeats.stream()
        .filter(s -> !s.isReserved())
        .collect(Collectors.toSet())
        .size();

        if (unreservedTicketCount > 0) {
            throw new IllegalStateException("Cannot create a ticket with unreserved seats");
        }
    }

    /**
     * Generate ticket IDs of the format GIC0001, GIC0002 etc.
     * @param counter: 
     * @return 
     */
    private String generateTicketID(int ticketCounter){
        if (ticketCounter > 9999) {
            throw new IllegalStateException("Ticket counter is not expected to be more than 4 digits!!!");
        }
        return String.format("%s%04d", TICKET_PREFIX, ticketCounter);
    }
}