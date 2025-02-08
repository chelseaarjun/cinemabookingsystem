package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class TheaterTest {

    /**
     * Tests that a ticket is generated properly and can be retrieved using its ticket ID.
     */
    @Test
    public void testGenerateAndRetrieveTicket() {
        Theater theater = new Theater("Inception", 5, 10);
        Seat a1 = Seat.createUnReservedSeat(1, 1);
        Seat a2 = Seat.createUnReservedSeat(1, 2);
        Set<Seat> selectedSeats = Set.of(a1, a2);

        Ticket ticket = theater.generateTicket(selectedSeats);
        assertNotNull("Generated ticket should not be null", ticket);

        Optional<Ticket> retrievedTicket = theater.getBookedTicket(ticket.getTicketId());
        assertTrue( "Ticket should be present in the booked tickets map", retrievedTicket.isPresent());
        assertEquals("Retrieved ticket should match the generated ticket", retrievedTicket.get(), ticket);
    }

    /**
     * Tests that trying to retrieve a non-existent ticket causes a NullPointerException.
     * <p>
     * Note: Because getBookedTicket uses Optional.of instead of Optional.ofNullable,
     * passing a non-existent ticket ID will throw an exception.
     * </p>
     */
    @Test
    public void testGetBookedTicket_NotFound() {
        Theater theater = new Theater("Inception", 5, 10);
        Optional<Ticket> retrievedTicket = theater.getBookedTicket("non-existent-id");

        assertTrue("Ticket should be empty when trying to retrieve a non existent id", retrievedTicket.isEmpty());
    }
}
