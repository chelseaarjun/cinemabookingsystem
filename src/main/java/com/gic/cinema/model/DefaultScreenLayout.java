package com.gic.cinema.model;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
class DefaultScreenLayout extends ScreenLayout {

    DefaultScreenLayout(Screen screen) {
      super(screen);
    }

     /**
     * Returns the next n available seats starting from the furthest row from the screen and middle-most possible seat within in the row. All seats in a row has been exhausted
     * it will overflow to next row.
     * @param n the number of available seats to retrieve
     * @return a list of available seat identifiers (e.g., "A1", "A2", "B1", etc.) up to n seats.
     */
    @Override
    public Set<Seat> getNextAvailableSeats(int n) {
        Set<Seat> availableSeats = new HashSet<>();
        /*for (int i =  getScreen().getFirstRowIndex(); i < getScreen().getLastRowIndex() && availableSeats.size() < n; i++) {
            for (int j = getScreen().getFirtRowNumber(); j < getScreen().getLastRowNumber() && availableSeats.size() < n; j++) {
                if (!seatAvailability[i][j]) {
                    char rowLetter = (char) ('A' + i);
                    availableSeats.add(rowLetter + Integer.toString(j + 1));
                }
            }
        }*/
        return availableSeats;
    }

     /**
     * Seats are filled up starting from the given seats. Seats from the same row all the way to the right are filled up first and the overflows to the next row. 
     * 
     * @param startSeatId the seat identifier from which to start (e.g., "B3")
     * @param n         the number of available seats to retrieve
     * @return a list of available seat identifiers starting from the given seat, up to n seats.
     * @throws IllegalArgumentException if the provided startSeat is invalid.
     */
    @Override
    public Set<Seat> getNextAvailableSeats(String startSeatId, int n) {
        Set<Seat> availableSeats = new HashSet<>();

        // Parse startSeat to find the starting position.
        /*int[] pos = screen.parseSeatId(startSeatId);
        if (pos == null) {
            throw new IllegalArgumentException("Invalid start seat identifier: " + startSeatId);
        }
        int startRow = pos[0];
        int startCol = pos[1];

        for (int i = startRow; i < screen.getRows() && availableSeats.size() < n; i++) {
            // For the starting row, begin with the specified column; otherwise, start at column 0.
            int jStart = (i == startRow) ? startCol : 0;
            for (int j = jStart; j < screen.getSeatsPerRow() && availableSeats.size() < n; j++) {
                if (!seatAvailability[i][j]) {
                    char rowLetter = (char) ('A' + i);
                    availableSeats.add(rowLetter + Integer.toString(j + 1));
                }
            }
        }*/
        return availableSeats;
    }  
}
