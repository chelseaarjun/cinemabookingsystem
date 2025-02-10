package com.gic.cinema.model;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class DefaultScreenLayoutTest {
    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testSelectionStartingAtWithNoOverflow() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 1, 1, 10);
        Set<Seat> expectedSeats = Set.of(new Seat(1,1), new Seat(1,2), new Seat (1,3));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(3, new Seat(1,1), Set.of());
        assertEquals(3, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testSelectionWithAlreadyReservedSeatsStartingAtWithNoOverflow() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 1, 1, 10);
        Set<Seat> alreadyReservedSeats = Set.of(new Seat(1,1), new Seat(1,2), new Seat (1,3));
        Set<Seat> expectedSeats = Set.of(new Seat(1,4), new Seat(1,5), new Seat (1,6));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(3, new Seat(1,1), alreadyReservedSeats);
        assertEquals(3, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testSelectionStartingAtWithOverflow() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 3, 1, 3);
        Set<Seat> expectedSeats = Set.of(new Seat(1,1), new Seat(1,2), new Seat (1,3), new Seat (2, 2));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(4, new Seat(1,1), Set.of());
        assertEquals(4, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testGetNextAvailableSeatsNoReserved() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 1, 1, 10);
        Set<Seat> expectedSeats = Set.of(new Seat(1,4), new Seat(1,5), new Seat (1,6), new Seat (1,7),  new Seat (1,8));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(5, Set.of());
        assertEquals(5, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testGetNextAvailableEvenOddPerRow() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 1, 1, 11);
        Set<Seat> expectedSeats = Set.of(new Seat(1,4), new Seat(1,5), new Seat (1,6), new Seat (1,7),  new Seat (1,8));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(5, Set.of());
        assertEquals(5, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

    /**
     * Test getNextAvailableSeats(int, Set) when no seats are reserved.
     * Expect that the returned set contains exactly the requested number of seats.
     */
    @Test
    public void testGetNextAvailableWithOverFlow() {
        DefaultScreenLayout layout = new DefaultScreenLayout(1, 2, 1, 5);
        Set<Seat> expectedSeats = Set.of(new Seat(1,3), new Seat(1,4), new Seat (1,2), new Seat (1,5),  new Seat (1,1), new Seat (2,3), new Seat (2,4));
        
        Set<Seat> selectedSeats = layout.getNextAvailableSeats(7, Set.of());
        assertEquals(7, selectedSeats.size());
        assertEquals(expectedSeats, selectedSeats);
    }

}