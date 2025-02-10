package com.gic.cinema.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

public class DefaultScreenTest {
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

        invalidStrings.forEach(invalidString -> {
            Exception exception = assertThrows(String.format("%s was not considered an invalid seat id!", invalidString), IllegalArgumentException.class,
            () -> screen.parseSeatId(invalidString));

            assertEquals(exception.getMessage(), (Screen.INVALID_SEAT_MESSSAGE));
        });
    }

    @Test
    public void testSeatNumberGenerationThrowsException() {
        Screen screen = new DefaultScreen (2, 5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> screen.getSeatId(new Seat(27, 5)));
        assertEquals(exception.getMessage(), Screen.INVALID_SEAT_MESSSAGE);
    }

    @Test
    public void testSeatIdGeneration() {
        Screen screen = new DefaultScreen (2, 5);
        Set<Seat> validSeats = Set.of(new Seat(1,1), new Seat(2,5));
        Set<String> expectedSeatIds = Set.of("A1", "B5");
        Set<String> actualSeatIds = validSeats.stream().map(seat -> screen.getSeatId(seat)).collect(Collectors.toSet());

        assertEquals(expectedSeatIds, actualSeatIds);
    }
}