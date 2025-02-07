package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents a movie ticket, encapsulating one or more seats and providing functionality
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
    private final String screeId;
    private final List<Seat> seats;

    public Ticket(@NonNull String ticketId, @NonNull String screeId, @NonNull List<Seat> seats) {
        this.ticketId = ticketId;
        this.screeId = screeId;
        this.seats = new ArrayList<>(seats);
    }

    /**
     * Generate ticket IDs of the format GIC0001, GIC0002 etc.
     * @param counter: 
     * @return 
     */
    public static String generateTicketID(final int counter){
        if (counter > 9999) {
            throw new IllegalStateException("Ticket counter is not expected to be more than 4 digits!!!");
        }
        return String.format("%s%04d", TICKET_PREFIX, counter);
    }
}