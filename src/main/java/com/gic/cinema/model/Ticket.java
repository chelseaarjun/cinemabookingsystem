package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ticket {
    private final static String TICKET_PREFIX = "GIC"; 
    private final List<Seat> seats;

    public Ticket(List<Seat> seats) {
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
        return String.format("%s%04d", TICKET_PREFIX, counter + 1);
    }
}