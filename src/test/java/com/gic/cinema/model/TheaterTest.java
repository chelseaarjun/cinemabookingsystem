package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class TheaterTest {

    /**
     * Tests that a ticket can be retrieved using its ticket ID amd contains the correct seats
     */
    @Test
    public void testGenerateAndRetrieveTicket() {
        Theater theater = new Theater("Inception", 5, 10);
        Seat a1 = theater.getScreen().generateSeat(1, 1);
        Seat a2 = theater.getScreen().generateSeat(1, 2);
        Set<Seat> selectedSeats = Set.of(a1, a2);

        String ticketId = theater.generateTicketID();
        assertNotNull("Generated ticket should not be null", ticketId);

        theater.confirmBooking(ticketId, selectedSeats);
        Optional<Ticket> retrievedTicket = theater.getBookedTicket(ticketId);
       
        assertTrue(retrievedTicket.isPresent());
        assertEquals(retrievedTicket.get().getTicketId(), "GIC0001");
        assertEquals(retrievedTicket.get().getReservedSeats().size(), 2);
        assertTrue(retrievedTicket.get().getReservedSeats().contains(a1));
        assertTrue(retrievedTicket.get().getReservedSeats().contains(a2));
    }

    /**
     * Tests that a ticket id is generated properly.
     */
    @Test
    public void testTicketIDGeneration() {
        Theater theater = new Theater("Inception", 5, 10);
        Seat a1 = theater.getScreen().generateSeat(1, 1);
        Seat a2 = theater.getScreen().generateSeat(1, 2);

        theater.confirmBooking(theater.generateTicketID(), Set.of(a1));
        theater.confirmBooking(theater.generateTicketID(), Set.of(a2));

        Optional<Ticket> ticket1 = theater.getBookedTicket("GIC0001");
        assertTrue( "Ticket should be present in the booked tickets map", ticket1.isPresent());
        assertEquals("Retrieved ticket should match the generated ticket", ticket1.get().getTicketId(), "GIC0001");

        Optional<Ticket> ticket2 = theater.getBookedTicket("GIC0002");
        assertTrue( "Ticket should be present in the booked tickets map", ticket2.isPresent());
        assertEquals("Retrieved ticket should match the generated ticket", ticket2.get().getTicketId(), "GIC0002");
    }

    /**
     * Tests that is trying to retrieve a non-existent ticket causes a NullPointerException.
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
