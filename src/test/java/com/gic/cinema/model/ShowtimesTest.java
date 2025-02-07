package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class ShowtimesTest {
    /**
     * Verifies that a Showtime is initialized correct number of seats.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new Screen("1", 10, 10);
        Showtime showtimes = new Showtime("TestMovie", screen);
        Map<String, Seat> seats = showtimes.getSeats();

        assertEquals("There should be 100 seats (10x10)", seats.size(), 100);
    }

     /**
     * Verifies correct generation of seat numbers for a small screen.
     */
    @Test
    public void testSeatGeneration() {
        Screen screen = new Screen("1", 2, 3);
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
}
