package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ShowtimesTest {
    final static String MOVIE_NAME = "TestMovie";
    final Seat a1 = Seat.createUnReservedSeat(1, 1);
    final Seat a2 = Seat.createUnReservedSeat(1, 2);
    final Seat b1 = Seat.createUnReservedSeat(2, 1);
    final Seat b2 = Seat.createUnReservedSeat(2, 2);
    /**
     * Verifies that a Showtime is initialized with correct number of seats.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new Screen(10, 10);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        Map<String, Seat> seats = showtimes.getSeats();

        assertEquals("There should be 100 seats (10x10)", seats.size(), 100);
    }

     /**
     * Verifies seat numbers are initialized correctly for a small screen.
     */
    @Test
    public void testSeatGeneration() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        Map<String, Seat> seats = showtimes.getSeats();

        // Check seat number contains Row 1: A1, A2, A3; Row 2: B1, B2, B3.
        assertTrue("Seat should contain 'A1'", seats.containsKey("A1"));
        assertTrue("Seat should contain 'A2'", seats.containsKey("A2"));
        assertTrue("Seat should contain 'A3'", seats.containsKey("A3"));
        assertTrue("Seat should contain 'B1'", seats.containsKey("B1"));
        assertTrue("Seat should contain 'B2'", seats.containsKey("B2"));
        assertTrue("Seat should contain 'B3'", seats.containsKey("B3"));
    }

    /**
     * Verifies the correct seats are getting reserved.
     */
    @Test
    public void testSetSeatReservation() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        Seat a1 = Seat.createUnReservedSeat(1, 1);
        Seat a2 = Seat.createUnReservedSeat(1, 2);
        Seat b1 = Seat.createUnReservedSeat(2, 1);

        showtimes.reserveSeats(Set.of(a2, b1));

        assertTrue("Seat 'A2' should be reserved", showtimes.isSeatReserved(a2.reserve()));
        assertTrue("Seat 'B1' should be reserved", showtimes.isSeatReserved(b1.reserve()));
        assertFalse("Seat 'A1' should not be reserved", showtimes.isSeatReserved(a1.reserve()));
    }

    /**
     * Verifies the correct reserved seats are getting returned.
     */
    @Test
    public void testGetSeatReservation() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime(MOVIE_NAME, screen);
        final Seat a1 = Seat.createUnReservedSeat(1, 1);
        final Seat b2 = Seat.createUnReservedSeat(2, 2);

        showtimes.reserveSeats(Set.of(a1, b2));
        Set<Seat> reservedSeats = showtimes.getReservedSeats();

        assertTrue("Exactly 2 seats should have been reserved!", reservedSeats.size() == 2);
        assertTrue("Seat 'A1' should have been reserved", reservedSeats.contains(a1.reserve()));
        assertTrue("Seat 'B2' should have been reserved", reservedSeats.contains(b2.reserve()));
    }
}
