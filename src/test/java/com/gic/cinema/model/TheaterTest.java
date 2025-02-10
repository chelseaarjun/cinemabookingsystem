package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class TheaterTest {

    /**
     * Tests that a ticket id is generated properly.
     */
    @Test
    public void testTicketIDGeneration() {
        Theater theater = new Theater("Inception", 5, 10);
        Seat a1 = new Seat(1, 1);
        Seat a2 = new Seat(1, 2);

        Ticket expectedTicket1 = theater.generateTicket(Set.of(a1));
        theater.confirmTicket(expectedTicket1);

        Ticket expectedTicket2 = theater.generateTicket(Set.of(a2));
        theater.confirmTicket(expectedTicket2);

        assertEquals("Expected 2 booked tickets", 2, theater.getTickets().size());
            
        Optional<Ticket> actualTicket1 = theater.getBookedTicket("GIC0001");
        assertTrue("Could not find Booking ID GIC0001", actualTicket1.isPresent());
        assertEquals("Retrieved ticket should match the generated ticket", expectedTicket1, actualTicket1.get());

        Optional<Ticket> actualTicket2 = theater.getBookedTicket("GIC0002");
        assertTrue("Could not find Booking ID GIC0002", actualTicket1.isPresent());
        assertEquals("Retrieved ticket should match the generated ticket", expectedTicket2, actualTicket2.get());
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

    /**
     *  * Test case to verify that the ticket ID is generated in the correct format.
     */
    @Test
    public void ticketIdFormatCorrect() {
        Theater theater = new Theater("Inception", 5, 10);
        String ticketid = theater.generateTicketID();
        assertEquals("GIC0001", ticketid);
    }
}
