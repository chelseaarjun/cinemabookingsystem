package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Set;

import org.junit.Test;

public class TicketTest {
    private final Screen screen = new Screen(2, 4);
    private final Showtime showtimes = new Showtime("TestMovie", screen);
    
    /**
     *  * Test case to verify that the ticket ID is generated in the correct format.
     */
    @Test
    public void ticketIdFormatCorrect() {
        String ticketid = Ticket.generateTicketID(5);
        assertEquals("GIC0005", ticketid);
    }

     /**
     *  * Test case to verify that the ticket cannot be generated with unreserved seats
     */
    @Test
    public void tryToGenerateTicketWithUnreservedSeats() {
        Seat a1 = Seat.createUnReservedSeat(1, 1);
        Seat b1 = Seat.createUnReservedSeat(2, 1);
        Set<Seat> unReservedSeats = Set.of(a1, b1);
      
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new Ticket("GIC0001", showtimes, unReservedSeats);
        });
        assertEquals("Cannot create a ticket with unreserved seats", exception.getMessage());
    }

    @Test
    public void ticketIdGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Ticket.generateTicketID(10000);
        });
        assertEquals("Ticket counter is not expected to be more than 4 digits!!!", exception.getMessage());
    }
}

