package com.gic.cinema.model;

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
abstract class ScreenLayout {
    final int firstRowIndex;
    final int firtSeatPosIndex;
    final int totalRows;
    final int seatsPerRow;
            
    /**
     * Constructs a new Screen Layout
     * <p>
     * This constructor initializes the seating arrangement based on the provided
     * number of rows and seats per row.
     * </p>
     * 
     * @param screen the screen associated with this layout
     * @throws IllegalArgumentException if {@code rows} is greater than {@code MAX_ROWS} or
     *                                  {@code seatsPerRow} is greater than {@code MAX_SEATS_PER_ROW}.
     */
    ScreenLayout(int firstRowIndex, int totalRows, int firtSeatPosIndex, int seatsPerRow) {
        this.firstRowIndex = firstRowIndex;
        this.totalRows = totalRows;
        this.firtSeatPosIndex = firtSeatPosIndex;
        this.seatsPerRow = seatsPerRow;
    }

    /**
     * Returns a list of the next n available seats in the layout using a default row-major ordering (e.g., "A1", "A2", "A3", etc.).
     *
     * @param numSeats the number of available seats to retrieve
     * @return a list of seats representing the next available seats. If fewer than n seats are available, returns all available seats.
     */
    abstract Set<Seat> getNextAvailableSeats(int numSeats,  Set<Seat> resevedSeats);

     /**
     *  Returns a list of the next n available seats in the layout using a default row-major ordering starting from a row.
     * 
     * @param numSeats the number of available seats to retrieve
     * @param startingRowIndex the starting row index
     * @param resevedSeats the set of reserved seats to exclude from the search
     * @return a list of seats representing the next available seats. If fewer than n seats are available, returns all available seats.
     */
    abstract Set<Seat> getNextAvailableSeats(int numSeats, int startingRowIndex, Set<Seat> resevedSeats);

    /**
     * Returns a list of the next n available seats starting from a given seat.
     *
     * @param numSeats the number of available seats to retrieve
     * @param startingSeat the seat  from which to begin the search
     * @param resevedSeats the set of reserved seats to exclude from the search
     * @return a list of seat identifiers representing the next available seats starting from the given seat.
     *         If fewer than n seats are available after the specified start, returns all available seats found.
     */
    abstract Set<Seat> getNextAvailableSeats(int numSeats, Seat startingSeat, Set<Seat> resevedSeats);

}
