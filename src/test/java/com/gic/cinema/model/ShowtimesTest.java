package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ShowtimesTest {
    /**
     * Verifies that a Showtime is initialized with correct number of seats.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new Screen(10, 10);
        Showtime showtimes = new Showtime("TestMovie", screen);
        Map<String, Seat> seats = showtimes.getSeats();

        assertEquals("There should be 100 seats (10x10)", seats.size(), 100);
    }

     /**
     * Verifies seat numbers are initialized correctly for a small screen.
     */
    @Test
    public void testSeatGeneration() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime("TestMovie", screen);
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
        Showtime showtimes = new Showtime("TestMovie", screen);

        showtimes.reserveSeats(Set.of("A2", "B1"));

        assertTrue("Seat 'A2' should be reserved", showtimes.isSeatReserved("A2"));
        assertTrue("Seat 'B1' should be reserved", showtimes.isSeatReserved("B1"));
        assertFalse("Seat 'B1' should be reserved", showtimes.isSeatReserved("B2"));
    }

    /**
     * Verifies the correct reserved seats are getting returned.
     */
    @Test
    public void testGetSeatReservation() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime("TestMovie", screen);

        showtimes.reserveSeats(Set.of("A1", "B2"));
        Set<String> reservedSeats = showtimes.getReservedSeats();

        assertTrue("Exactly 2 seats should have been reserved!", reservedSeats.size() == 2);
        assertTrue("Seat 'A1' should have been reserved", reservedSeats.contains("A1"));
        assertTrue("Seat 'B2' should have been reserved", reservedSeats.contains("B2"));
    }

    /**
     * Test reserving using invalid identifiers
     */
    @Test
    public void testInvalidIdReservation() {
        Screen screen = new Screen(2, 3);
        Showtime showtimes = new Showtime("TestMovie", screen);
        String invalidSeatId = "Anmdx";

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> showtimes.reserveSeats(Set.of(invalidSeatId)));
        assertTrue(exception.getMessage().contains("Seat number " + invalidSeatId + " is not valid for this screen"));
    }
}
