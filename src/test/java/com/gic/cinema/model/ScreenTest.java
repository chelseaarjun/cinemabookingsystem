package com.gic.cinema.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ScreenTest {

    /**
     * Verifies that a Screen is initialized correctly with valid input.
     * Checks that the movie name is set and the total number of seats equals rows * seatsPerRow.
     * Also checks that the first and last Seat objects have the expected seat numbers.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new Screen("1", "Inception", 10, 10);
        
        assertEquals("Movie name should be 'Inception'", screen.getMovieName(),"Inception");
        assertEquals("There should be 100 seats (10x10)", screen.getAllSeats().size(), 100);

        Seat firstSeat = screen.getAllSeats().get(0);
        assertEquals("First seat should be 'A1'", firstSeat.getSeatNumber(), "A1");

        Seat lastSeat = screen.getAllSeats().get(screen.getAllSeats().size() - 1);
        assertEquals("J10", lastSeat.getSeatNumber(), "Last seat should be 'J10'");
    }

    /**
     * Verifies that a Screen constructed with rows exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", "Inception", 27, 10));
            
        assertTrue(exception.getMessage().contains("maximum number of rows allowed is 26"));
    }

    /**
     * Verifies that a Screen constructed with seats per row exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", "Inception", 10, 51));
            
        assertTrue(exception.getMessage().contains("maximum number of seats allowed per row allowed is 50"));
    }

    /**
     * Verifies correct generation of seat numbers for a small screen.
     * In a Screen with 2 rows and 3 seats per row, expect seats to be ordered row-major.
     */
    @Test
    public void testSeatGeneration() {
        Screen screen = new Screen("1", "Inception", 2, 3);
        assertEquals("There should be 6 seats (2x3)", screen.getAllSeats().size(), 6);

        // Check order and expected seat number:
        // Row 1: A1, A2, A3; Row 2: B1, B2, B3.
        assertEquals("A1", screen.getAllSeats().get(0).getSeatNumber());
        assertEquals("A2", screen.getAllSeats().get(1).getSeatNumber());
        assertEquals("A3", screen.getAllSeats().get(2).getSeatNumber());
        assertEquals("B1", screen.getAllSeats().get(3).getSeatNumber());
        assertEquals("B2", screen.getAllSeats().get(4).getSeatNumber());
        assertEquals("B3", screen.getAllSeats().get(5).getSeatNumber());
    }
}