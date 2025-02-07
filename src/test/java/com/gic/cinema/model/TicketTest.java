package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;
import org.junit.Test;

public class TicketTest {

    @Test
    public void ticketIdFormatCorrect() {
        Ticket ticket = new Ticket(List.of(new Seat(1,1)));
        assertEquals("GIC0006", ticket.generateTicketID(5));
    }

    @Test
    public void ticketIdGenerationThrowsException() {
        Ticket ticket = new Ticket(List.of(new Seat(1,1)));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> ticket.generateTicketID(10000));
        assertEquals("Ticket counter is not expected to be more than 4 digits!!!", exception.getMessage());
    }

    @Test
    public void seatAssignmentCorrect() {
        List<Seat> seats = List.of(new Seat(2,3), new Seat(2,4));
        Ticket ticket = new Ticket(seats);
        assertEquals(2, ticket.getSeats().size());
        assertEquals("B3", ticket.getSeats().get(0).getSeatNumber());
        assertEquals("B4", ticket.getSeats().get(1).getSeatNumber());
    }
}

