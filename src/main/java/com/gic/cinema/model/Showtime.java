package com.gic.cinema.model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
 
/**
 * Represents a showtime for a movie in a specific screen.
 * <p>
 * The Showtime class encapsulates the movie name, screen, and seats for a showtime.
 * It provides methods to manage seat reservations and track the number of remaining
 * and reserved seats.
 * </p>
 */
@Getter
@EqualsAndHashCode
@ToString
public class Showtime {
    private final String movieName;
    private final Screen screen;
    private final Map<Seat,Boolean> seatAvailability; 

    /**
     * Constructs a new Showtime instance.
     *
     * @param movieName the name of the movie
     * @param screen    the screen for the showtime
     */
    public Showtime(String movieName, Screen screen) {
        this.movieName = movieName;
        this.screen = screen;
        this.seatAvailability = screen.initializeSeats().stream().collect(Collectors.toMap(e -> e, e -> false));
    }

    /**
     * Gets the available seat count for this showtime.
     * 
     * @return the number of remaining unreserved seats
     */
    public long getAvailableSeatCount() {
        return seatAvailability.values().stream().filter(isReserved -> !isReserved).count();
    }

     /**
     * Returns the next n available seats starting from the furthest row from the screen and middle-most possible seat within in the row. All seats in a row has been exhausted
     * it will overflow to next row.
     * @param numSeats the number of available seats to retrieve
     * @param startingRowIndex the starting row index
     * @return the next available set of seats
     */
    public Set<Seat> getNextAvailableSeats(int numSeats) {
        return screen.getScreenLayout().getNextAvailableSeats(numSeats, getReservedSeats());
    }

     /**
     * Seats are filled up starting from the given seats. Seats from the same row all the way to the right are filled up first and the overflows to the next row. 
     * 
     * @param startSeatId the seat identifier from which to start (e.g., "B3")
     * @param numSeats the number of available seats to retrieve
     * @return the next available set of seats
     * @throws IllegalArgumentException if the provided startSeat is invalid.
     */
    public Set<Seat> getNextAvailableSeats(int numSeats, String startSeatId) {
        Seat startingSeat = getScreen().parseSeatId(startSeatId);
        return screen.getScreenLayout().getNextAvailableSeats(numSeats, startingSeat, getReservedSeats());
    }  

    /**
     * Get the set of reserved seat identifiers for this showtime.
     * @return
     */
    public Set<Seat> getReservedSeats() {
        return  seatAvailability
            .entrySet().stream()
            .filter(entry -> entry.getValue())
            .map(entry -> entry.getKey())
            .collect(Collectors.toSet());
    }

    /**
     * Mark the specified seat identifiers as reserved.
     * @param selectedSeats set of selected seat identifiers for booking
     * @return true if all seats were reserved. False otherwise
     * @throws IlleagalStateException if any of the seats are already reserved
     */
    boolean reserveSeats(Set<Seat> selectedSeats) {
        return selectedSeats.stream().map(seat -> reserveSeat(seat)).allMatch(successs -> successs);
    }

    /**
     * Reserves the specified seats if it is available.
     *
     * @param seat the seat that needs to reserved
     * @return true if the seat was successfully reserved; false if it was already reserved or invalid.
     * @throws IlleagalStateException if any of the seats are already reserved
     */
    boolean reserveSeat(Seat seat) {
        //check if the seat is already reserved
        if (seatAvailability.get(seat)) {
            throw new IllegalStateException("Cannot reserve an already reserved Seat");
        }
        seatAvailability.put(seat, true);             
        return true;
    }
}