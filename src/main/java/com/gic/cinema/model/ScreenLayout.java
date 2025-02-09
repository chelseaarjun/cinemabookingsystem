package com.gic.cinema.model;

import java.util.Map;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A contract for maintaining a seating layout for a showtime.
 * Implementations of this interface define how available seats
 * are retrieved, allowing flexible changes to the seat-selection logic.
 */
@Getter
@EqualsAndHashCode
@ToString
public abstract class ScreenLayout {
    private final Screen screen;
    private final Map<Seat,Boolean> seatAvailability; 
            
    /**
     * Constructs a new Screen Layout
     * <p>
     * This constructor initializes the seating arrangement based on the provided
     * number of rows and seats per row. It validates that the number of rows does not exceed
     * {@code MAX_ROWS} and the number of seats per row does not exceed {@code MAX_SEATS_PER_ROW}. If these conditions are not met,
     * an {@link IllegalArgumentException} is thrown.
     * </p>
     * 
     * @param rows        the number of rows in the screen.
     * @param seatsPerRow the number of seats in each row.
     * @throws IllegalArgumentException if {@code rows} is greater than {@code MAX_ROWS} or
     *                                  {@code seatsPerRow} is greater than {@code MAX_SEATS_PER_ROW}.
     */
    ScreenLayout(Screen screen) {
        this.screen = screen;
        this.seatAvailability = screen.initializeSeats();
    }

    /**
     * Returns a list of the next n available seat identifiers in the layout,
     * using a default row-major ordering (e.g., "A1", "A2", "A3", etc.).
     *
     * @param n the number of available seats to retrieve
     * @return a list of seat identifiers representing the next available seats.
     *         If fewer than n seats are available, returns all available seats.
     */
    abstract Set<Seat> getNextAvailableSeats(int n);

    /**
     * Returns a list of the next n available seat identifiers starting from a given seat.
     *
     * @param startSeat the seat from which to begin the search (e.g., "B3")
     * @param n         the number of available seats to retrieve
     * @return a list of seat identifiers representing the next available seats starting from the given seat.
     *         If fewer than n seats are available after the specified start, returns all available seats found.
     */
    abstract Set<Seat> getNextAvailableSeats(String seatId, int n);

    /**
     * Reserves the specified seats if it is available.
     *
     * @param seatIds the seat identifiers (e.g., "A1")
     * @return true if the seat was successfully reserved; false if it was already reserved or invalid.
     */
    boolean reserveSeat(Seat seat) {
        //check if the seat is already reserved
        if (seatAvailability.get(seat)) {
            return false;
        }
        seatAvailability.put(seat, true);             
        return true;
    }
}
