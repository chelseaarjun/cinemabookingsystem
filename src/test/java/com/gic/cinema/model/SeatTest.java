package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class SeatTest {

    @Test
    public void initialReservationStatus() {
        Seat seat = new Seat(3, 2);
        assertFalse(seat.isReserved());
    }

    @Test
    public void testSeatNumberGeneration() {
        Seat seat = new Seat(1, 5);
        assertEquals("A5", seat.getSeatNumber());
        
        Seat seat2 = new Seat(26, 50);
        assertEquals("Z50", seat2.getSeatNumber());
    }

    @Test
    public void testSeatNumberGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> new Seat(27, 5));
        assertEquals("Can only generate seat numbers for upto 26 rows", exception.getMessage());
    }
}