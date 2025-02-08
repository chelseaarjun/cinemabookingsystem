package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SeatTest {

    @Test
    public void initialReservationStatus() {
        Seat seat = Seat.createUnReservedSeat(3, 2);
        assertFalse(seat.isReserved());
    }

    @Test
    public void reservedStatus() {
        Seat reservedSeat = Seat.createUnReservedSeat(3, 2).reserve();
        assertTrue(reservedSeat.isReserved());
    }

    @Test
    public void testSeatNumberGeneration() {
        Seat seat = Seat.createUnReservedSeat(1, 5);
        assertEquals("A5", seat.getSeatNumber());
        
        Seat seat2 = Seat.createUnReservedSeat(26, 50);
        assertEquals("Z50", seat2.getSeatNumber());
    }

    @Test
    public void testSeatNumberGenerationThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> Seat.createUnReservedSeat(27, 5));
        assertEquals("Can only generate seat numbers for upto 26 rows", exception.getMessage());
    }
}