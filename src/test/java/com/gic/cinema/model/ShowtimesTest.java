package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class ShowtimesTest {
    final static String MOVIE_NAME = "TestMovie";

     /**
     * Verifies that a Showtime is initialized with correct number of seats.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new DefaultScreen(10, 10);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);

        assertEquals("There should be 100 seats (10x10)", showtimes.getAvailableSeatCount(), 100);
        assertTrue("Expect 0 seats to be available", showtimes.getReservedSeats().size() == 0);
    }
    
    /**
    * Verifies seat numbers are initialized correctly for a small screen.
     */
    @Test
    public void testSeatInitialization() {
        Screen screen = new DefaultScreen(2, 3);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);

        // Check seat number contains Row 1: A1, A2, A3; Row 2: B1, B2, B3.
        assertTrue("Seat should contain 'A1'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("A1")));
        assertTrue("Seat should contain 'A2'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("A2")));
        assertTrue("Seat should contain 'A3'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("A3")));
        assertTrue("Seat should contain 'B1'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("B1")));
        assertTrue("Seat should contain 'B2'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("B2")));
        assertTrue("Seat should contain 'B3'", showtimes.getSeatAvailability().containsKey(screen.parseSeatId("B3")));
    }

    /**
     * Verifies the correct seats are getting reserved.
     */
    @Test
    public void testSetSeatReservation() {
        Screen screen = new DefaultScreen(10, 10);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        Seat a1 = new Seat(1, 1);
        Seat a2 = new Seat(1, 2);
        Seat b1 =new Seat(2, 1);

        showtimes.reserveSeats(Set.of(a2, b1));

        assertTrue("Seat 'A2' should be reserved", showtimes.getReservedSeats().contains(a2));
        assertTrue("Seat 'B1' should be reserved", showtimes.getReservedSeats().contains(b1));
        assertFalse("Seat 'A1' should not be reserved", showtimes.getReservedSeats().contains(a1));
    }

    /**
     * Verifies the correct seats are getting reserved.
     */
    @Test
    public void testSetReservingAlradyReservedSeat() {
        Screen screen = new DefaultScreen(10, 10);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        Seat a1 = new Seat(1, 1);

        showtimes.reserveSeats(Set.of(a1));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> showtimes.reserveSeats(Set.of(a1)));
        assertEquals(exception.getMessage(), "Cannot reserve an already reserved Seat");
    }
}
