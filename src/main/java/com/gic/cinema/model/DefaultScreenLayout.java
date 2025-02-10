package com.gic.cinema.model;

import java.util.HashSet;
import java.util.Optional;
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

    /**
     * Constructs a new DefaultScreenLayout Layout
     * <p>
     * This constructor initializes the seating arrangement based on the provided
     * </p>
     * 
     * @param screen the screen associated with this layout
     * @throws IllegalArgumentException if {@code rows} is greater than {@code MAX_ROWS} or
     *                                  {@code seatsPerRow} is greater than {@code MAX_SEATS_PER_ROW}.
     */
    DefaultScreenLayout(int firstRowIndex, int totalRows, int firtSeatPosIndex, int seatsPerRow) {
        super(firstRowIndex, totalRows, firtSeatPosIndex, seatsPerRow);
    }

     /**
     * Returns the next n available seats starting from the furthest row from the screen and middle-most possible seat within in the row. All seats in a row has been exhausted
     * it will overflow to next row.
     * @param numSeats the number of available seats to retrieve
     * @return the next available set of seats
     */
    @Override
    Set<Seat> getNextAvailableSeats(int numSeats, Set<Seat> resevedSeats) {
        return this.getNextAvailableSeats(numSeats, firstRowIndex, resevedSeats);
    }

    /**
     * Seats are filled up starting from the given seats. Seats from the same row all the way to the right are filled up first and the overflows to the next row. 
     * 
     * @param startSeatId the seat identifier from which to start (e.g., "B3")
     * @param numSeats the number of available seats to retrieve
     * @return the next available set of seats
     * @throws IllegalArgumentException if the provided startSeat is invalid.
     */
    @Override
    Set<Seat> getNextAvailableSeats(int numSeats, Seat startingSeat, Set<Seat> resevedSeats) {
        Set<Seat> selectedSeats = new HashSet<>();
        int remainingSeatsToSelect = numSeats;
        for (int seatPosIndex = startingSeat.getSeatPosIndex(); seatPosIndex <= seatsPerRow && remainingSeatsToSelect > 0; seatPosIndex++) {
            Seat nextSeat = new Seat(startingSeat.getRowIndex(), seatPosIndex);
            if(!resevedSeats.contains(nextSeat)){
                remainingSeatsToSelect --;
                selectedSeats.add(nextSeat);
            }                
        }
        if (remainingSeatsToSelect > 0) {
            int nextRowtoSearch = findNextRowtoSearch(startingSeat.getSeatPosIndex());
            Set<Seat> nextSetofReservedSeats = getNextAvailableSeats(remainingSeatsToSelect, nextRowtoSearch, resevedSeats);
            selectedSeats.addAll(nextSetofReservedSeats);
        }
        return selectedSeats;
    }  

    /**
     * Returns the next n available seats starting from the furthest row from the screen and middle-most possible seat within in the row. All seats in a row has been exhausted
     * it will overflow to next row.
     * @param numSeats the number of available seats to retrieve
     * @param startingRowIndex the starting row index
     * @return the next available set of seats
     */
    @Override
    Set<Seat> getNextAvailableSeats(int numSeats, int startingRowIndex, Set<Seat> resevedSeats) {
        Set<Seat> selectedSeats = new HashSet<>();
        int rowIndex = startingRowIndex;
        int remainingSeatsToSelect = numSeats;

        for (int i = firstRowIndex; i <= totalRows && remainingSeatsToSelect > 0; i++) {
          
            //if the last row has been reached but search has not been exhaused then wrap around to search from the first row
            int middleSeatPosIndex = firtSeatPosIndex + getSeatsPerRow()/2;
            Optional<Seat> middleSeat = isSeatAvaiable(rowIndex, middleSeatPosIndex, resevedSeats);
            if(remainingSeatsToSelect > 0 && middleSeat.isPresent()) {
                remainingSeatsToSelect --;
                selectedSeats.add(middleSeat.get());
            }

            int j = firtSeatPosIndex;
            int max =  seatsPerRow%2 == 0 ? seatsPerRow/2: seatsPerRow/2+1;
            //search the row
            while (j < max && remainingSeatsToSelect > 0) {
                int rightSeatPosIndex = middleSeatPosIndex + j;
                int leftSeatPosIndex = middleSeatPosIndex - j;
                Optional<Seat> rightSeat = isSeatAvaiable(rowIndex, rightSeatPosIndex, resevedSeats);
                if(remainingSeatsToSelect > 0 && rightSeat.isPresent()) {
                    remainingSeatsToSelect --;
                    selectedSeats.add(rightSeat.get());
                }

                Optional<Seat> leftSeat = isSeatAvaiable(rowIndex, leftSeatPosIndex, resevedSeats);
                if(remainingSeatsToSelect > 0 && rightSeat.isPresent()) {
                    remainingSeatsToSelect --;
                    selectedSeats.add(leftSeat.get());
                }
                j++;
            }
            rowIndex = findNextRowtoSearch(rowIndex);
        }
        return selectedSeats;
    }


    private int findNextRowtoSearch(int rowIndex) {
        int nextRow = rowIndex + 1;
        //if the last row has been reached but search has not been exhaused then wrap around to search from the first row
        if (nextRow >  getTotalRows()) {
            return (nextRow)/getTotalRows();
        } else {
           return nextRow;
        }
    }

    private Optional<Seat> isSeatAvaiable(int rowIndex, int seatPosIndex, Set<Seat> resevedSeats) {
        Seat seat = new Seat(rowIndex, seatPosIndex);
        if(!resevedSeats.contains(seat)) {
            return Optional.of(seat);
        }
        return Optional.empty();
    }
}
