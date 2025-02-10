package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class TicketTest {
    final static String MOVIE_NAME = "TestMovie";
    final static Screen SCREEN = new DefaultScreen(10, 10);
    final static Showtime SHOWTIME = new Showtime(MOVIE_NAME, SCREEN);

     /**
     * Tests that a ticket can be retrieved using its ticket ID amd contains the correct seats
     */
    @Test
    public void testtConfirmBooking() {
        Seat a1 = new Seat(1, 1);
        Seat a2 = new Seat(1, 2);
        Set<Seat> selectedSeats = Set.of(a1, a2);

        Ticket ticket = new Ticket("TicketID", SHOWTIME, selectedSeats);

        ticket.confirmBooking();
     
        assertEquals(2, ticket.getSeats().size());
        assertTrue(ticket.getSeats().contains(a1));
        assertTrue(ticket.getSeats().contains(a2));
    }
}

