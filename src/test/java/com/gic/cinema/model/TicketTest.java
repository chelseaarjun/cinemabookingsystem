package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;
import org.junit.Test;

public class TicketTest {
    private final Screen screen = new Screen("TestScreen", 2, 4);
    private final List<String> seatNumbers = List.of("A0", "A1");
    private final Showtime showtimes = new Showtime("TestMovie", screen);

    @Test
    public void ticketIdFormatCorrect() {
        Ticket ticket = Ticket.createTicket(showtimes, seatNumbers, 5);
        assertEquals("GIC0005", ticket.getTicketId());
    }

    @Test
    public void ticketIdGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Ticket.createTicket(showtimes, seatNumbers, 10000);
        });
        assertEquals("Ticket counter is not expected to be more than 4 digits!!!", exception.getMessage());
    }
}

