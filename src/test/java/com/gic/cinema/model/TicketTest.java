package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Set;

import org.junit.Test;

public class TicketTest {
    private final Screen screen = new Screen(2, 4);
    private final Set<String> seats = Set.of("A1", "B1");
    private final Showtime showtimes = new Showtime("TestMovie", screen);

    /**
     *  * Test case to verify that the ticket ID is generated in the correct format.
     */
    @Test
    public void ticketIdFormatCorrect() {
        Ticket ticket = new Ticket(showtimes, seats, 5);
        assertEquals("GIC0005", ticket.getTicketId());
    }

    @Test
    public void ticketIdGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new Ticket(showtimes, seats, 10000);
        });
        assertEquals("Ticket counter is not expected to be more than 4 digits!!!", exception.getMessage());
    }
}

