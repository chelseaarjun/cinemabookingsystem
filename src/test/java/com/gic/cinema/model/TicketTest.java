package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TicketTest {
    /**
     *  * Test case to verify that the ticket ID is generated in the correct format.
     */
    @Test
    public void ticketIdFormatCorrect() {
        String ticketid = Ticket.generateTicketID(5);
        assertEquals("GIC0005", ticketid);
    }

    @Test
    public void ticketIdGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Ticket.generateTicketID(10000);
        });
        assertEquals("Ticket counter is not expected to be more than 4 digits!!!", exception.getMessage());
    }
}

