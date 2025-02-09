package com.gic.cinema.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class ShowtimesTest {
    final static Screen TEST_SCREEN = new DefaultScreen(10, 20);
    final static String MOVIE_NAME = "TestMovie";

    /**
     * Verifies the all seats are marked available after a showtime is initialized.
     */
    @Test
    public void testAllSeatsAreAvailable() {
        ScreenLayout screenLayout = new DefaultScreenLayout(TEST_SCREEN);
        Showtime showtimes = new Showtime(MOVIE_NAME, screenLayout);
 
        assertTrue("Expect 200 seats to be available", showtimes.getAvailableSeatCount() == 200);
        assertTrue("Expect 0 seats to be available", showtimes.getReservedSeats().size() == 0);
    }

    /**
     * Verifies the correct seats are getting reserved.
     */
    @Test
    public void testSetSeatReservation() {
        ScreenLayout screenLayout = new DefaultScreenLayout(TEST_SCREEN);
        Showtime showtimes = new Showtime(MOVIE_NAME, screenLayout);
        Seat a1 = TEST_SCREEN.parseSeatId("A1");
        Seat a2 = TEST_SCREEN.parseSeatId("A2");
        Seat b1 = TEST_SCREEN.parseSeatId("b1");

        showtimes.reserveSeats(Set.of(a2, b1));

        assertTrue("Seat 'A2' should be reserved", showtimes.getReservedSeats().contains(a2));
        assertTrue("Seat 'B1' should be reserved", showtimes.getReservedSeats().contains(b1));
        assertFalse("Seat 'A1' should not be reserved", showtimes.getReservedSeats().contains(a1));
    }
}
