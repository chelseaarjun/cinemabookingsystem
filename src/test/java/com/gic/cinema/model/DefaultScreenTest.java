package com.gic.cinema.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

public class DefaultScreenTest {

    /**
     * Verifies that a Showtime is initialized with correct number of seats.
     */
    @Test
    public void testValidInitialization() {
        Screen screen = new DefaultScreen(10, 10);
        Map<Seat, Boolean> seats = screen.initializeSeats();

        assertEquals("There should be 100 seats (10x10)", seats.size(), 100);
    }

     /**
     * Verifies seat numbers are initialized correctly for a small screen.
     */
    @Test
    public void testSeatInitialization() {
        Screen screen = new DefaultScreen(2, 3);
        Map<Seat, Boolean> seats = screen.initializeSeats();

        // Check seat number contains Row 1: A1, A2, A3; Row 2: B1, B2, B3.
        assertTrue("Seat should contain 'A1'", seats.containsKey(screen.parseSeatId("A1")));
        assertTrue("Seat should contain 'A2'", seats.containsKey(screen.parseSeatId("A2")));
        assertTrue("Seat should contain 'A3'", seats.containsKey(screen.parseSeatId("A3")));
        assertTrue("Seat should contain 'B1'", seats.containsKey(screen.parseSeatId("B1")));
        assertTrue("Seat should contain 'B2'", seats.containsKey(screen.parseSeatId("B2")));
        assertTrue("Seat should contain 'B3'", seats.containsKey(screen.parseSeatId("B3")));
    }

    /**
     * Verifies that a Screen constructed with rows exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(27, 10));
            
        assertTrue(exception.getMessage().contains("Values between 1 and 26 is expected for rows"));
    }

    /**
     * Verifies that a Screen constructed with seats per row exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(10, 51));
            
        assertTrue(exception.getMessage().contains("values between 1 and 50 expected for seat per row"));
    }

     /**
     * Verifies that a Screen constructed with rows less than zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(-1, 1));
            
        assertTrue(exception.getMessage().contains("Values between 1 and 26 is expected for rows"));
    }

    /**
     * Verifies that a Screen constructed with rows equal to zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(0, 1));
            
        assertTrue(exception.getMessage().contains("Values between 1 and 26 is expected for rows"));
    }

    /**
     * Verifies that a Screen constructed with seats per row less than zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(10, -1));
            
        assertTrue(exception.getMessage().contains("values between 1 and 50 expected for seat per row"));
    }

    /**
     * Verifies that a Screen constructed with seats per row equal to zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new DefaultScreen(10, 0));
            
        assertTrue(exception.getMessage().contains("values between 1 and 50 expected for seat per row"));
    }
   
    @Test
    public void testInvalidParseStringIds() {
        Set<String> invalidStrings = Set.of("A", "AA01", "ABC", "123", "####", "C1", "A6", "A0", "a100", "") ;
        Screen screen = new DefaultScreen (2, 5);
        for (String invalidString : invalidStrings) {
            Exception exception = assertThrows(String.format("%s was not considered an invalid seat id!", invalidString), IllegalArgumentException.class,
            () -> screen.parseSeatId(invalidString));

            assertEquals(exception.getMessage(), (Screen.INVALID_SEAT_MESSSAGE));
        }
    }

    @Test
    public void testSeatNumberGenerationThrowsException() {
        Screen screen = new DefaultScreen (2, 5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> screen.generateSeat(27, 5));
        assertEquals(exception.getMessage(), Screen.INVALID_SEAT_MESSSAGE);
    }
}