package com.gic.cinema.model;

import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ScreenTest {
    /**
     * Verifies that a Screen constructed with rows exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", 27, 10));
            
        assertTrue(exception.getMessage().contains("maximum number of rows allowed is 26"));
    }

    /**
     * Verifies that a Screen constructed with seats per row exceeding the maximum throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowExceedMax() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", 10, 51));
            
        assertTrue(exception.getMessage().contains("maximum number of seats allowed per row allowed is 50"));
    }

     /**
     * Verifies that a Screen constructed with rows less than zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", -1, 1));
            
        assertTrue(exception.getMessage().contains("Values between 1 and 26 is expected for rows"));
    }

    /**
     * Verifies that a Screen constructed with rows equal to zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidRowsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", 0, 1));
            
        assertTrue(exception.getMessage().contains("Values between 1 and 26 is expected for rows"));
    }

    /**
     * Verifies that a Screen constructed with seats per row less than zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", 10, -1));
            
        assertTrue(exception.getMessage().contains("values between 1 and 50 expected for seat per row"));
    }

    /**
     * Verifies that a Screen constructed with seats per row equal to zero throws an IllegalArgumentException.
     */
    @Test
    public void testInvalidSeatsPerRowZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Screen("1", 10, 0));
            
        assertTrue(exception.getMessage().contains("values between 1 and 50 expected for seat per row"));
    }
   
}