package com.gic.cinema.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
@ToString
public class Showtime {
    private final String movieName;
    private final Screen screen;
    private final Map<String, Seat> seats;

    /**
     * Constructs a new Showtime instance.
     *
     * @param movieName the name of the movie
     * @param screen    the screen for the showtime
     */
    public Showtime(String movieName, Screen screen) {
        this.movieName = movieName;
        this.screen = screen;
        this.seats = initializeSeats();
    }

    /**
     * Gets the number of remaining seats for this showtime.
     * 
     * @return the number of remaining unreserved seats
     */
    public int getNumRemainingSeats() {
        return  seats.values().stream()
            .filter(s -> s.isReserved())
            .map(s -> s.getSeatNumber())
            .collect(Collectors.toList())
            .size();
    }
    
    /**
     * Get the set of reserved seat identifiers for this showtime.
     * @return
     */
    public Set<String> getReservedSeats() {
        return  seats.values().stream()
            .filter(s -> s.isReserved())
            .map(s -> s.getSeatNumber())
            .collect(Collectors.toSet());
    }

    /**
     * Mark the specified seat identifiers as reserved.
     * @param selectedSeats set of selected seat identifiers for booking
     * @throws IllegalArgumentException if any of the seat identifiers are invalid
     */
    public void reserveSeats(Set<String> selectedSeats) {
        selectedSeats.forEach(seatNumber -> {
            if (!seats.containsKey(seatNumber)) {
                throw new IllegalArgumentException("Seat number " + seatNumber + " is not valid for this screen");
            }
            seats.put(seatNumber,  seats.get(seatNumber).reserve());
        });
    }

     /**
     * Check if the specified seat identifier is reserved.
     * @param selectedSeats seat identifier 
     * @return true if the seat is reserved, false otherwise
     * @throws IllegalArgumentException if the seat identifier is invalid
     */
    public boolean isSeatReserved(String seatNumber) {
        if (!seats.containsKey(seatNumber)) {
            throw new IllegalArgumentException("Seat number " + seatNumber + " is not valid for this screen");
        }
        return seats.get(seatNumber).isReserved();
    }

    /**
     * Initialize the seats for this showtime.
     * @return
     */
    private Map<String, Seat> initializeSeats() {
        Map<String, Seat> seats = new HashMap<String, Seat>();
        for (int row = 1; row <= screen.getRows(); row++) {
            for (int seatNum = 1; seatNum <= screen.getSeatsPerRow(); seatNum++) {
                Seat seat = Seat.createUnReservedSeat(row, seatNum);
                seats.put(seat.getSeatNumber(), seat);
            }
        }
        return seats;
    }
}