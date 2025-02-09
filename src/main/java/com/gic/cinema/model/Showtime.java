package com.gic.cinema.model;

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
    private final ScreenLayout screenLayout;

    /**
     * Constructs a new Showtime instance.
     *
     * @param movieName the name of the movie
     * @param screen    the screen for the showtime
     */
    public Showtime(String movieName, ScreenLayout screenLayout) {
        this.movieName = movieName;
        this.screenLayout = screenLayout;
    }

    /**
     * Gets the available seat count for this showtime.
     * 
     * @return the number of remaining unreserved seats
     */
    public long getAvailableSeatCount() {
        return screenLayout.getSeatAvailability()
            .values().stream().filter(isReserved -> !isReserved).count();
    }

    /**
     * Gets the available seat count for this showtime.
     * 
     * @return next n available seats
     */
    public Set<Seat> getAvailableSeats(int n) {
        return screenLayout.getNextAvailableSeats(n);
    }

    public Set<Seat> getAvailableSeats(String seatId, int n)  {
        return screenLayout.getNextAvailableSeats(seatId, n);
    }

    /**
     * Get the set of reserved seat identifiers for this showtime.
     * @return
     */
    public Set<Seat> getReservedSeats() {
        return  screenLayout.getSeatAvailability()
            .entrySet().stream()
            .filter(entry -> entry.getValue())
            .map(entry -> entry.getKey())
            .collect(Collectors.toSet());
    }

    /**
     * Mark the specified seat identifiers as reserved.
     * @param selectedSeats set of selected seat identifiers for booking
     * @return the set of reserved seats
     * @throws IllegalArgumentException if any of the seat identifiers are invalid
     */
    Set<Seat> reserveSeats(Set<Seat> selectedSeats) {
        selectedSeats.forEach(seat -> {
            screenLayout.reserveSeat(seat);
        });
        return getReservedSeats();
    }
}