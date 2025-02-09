package com.gic.cinema.model;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a movie screen with its seating configurations in a cinema theater.
 *
 */
@Getter
@EqualsAndHashCode
@ToString
abstract class Screen {
    static final String INVALID_SEAT_MESSSAGE = "Invalid Seat number. Try again.";
    private final int maxSeatsPerRow;
    private final int maxRows;
    @Getter private final int firstRowIndex = 1;
    @Getter private final int firtRowNumber = 1;
    private final int lastRowIndex;
    private final int lastRowNumber;

      /**
     * Constructs a new Screen instance with specified number of rows and seats.
     * <p>
     * This constructor initializes based on the provided number of rows and seats per row. It validates that the number of rows does not exceed
     * {@code MAX_ROWS} and the number of seats per row does not exceed {@code MAX_SEATS_PER_ROW}. If these conditions are not met,
     * an {@link IllegalArgumentException} is thrown.
     * </p>
     * 
     * @param rows        the number of rows in the screen.
     * @param seatsPerRow the number of seats in each row.
     * @throws IllegalArgumentException if {@code rows} is greater than {@code MAX_ROWS} or
     *                                  {@code seatsPerRow} is greater than {@code MAX_SEATS_PER_ROW}.
     */
    Screen(int rows, int seatsPerRow, int maxRows, int maxSeatsPerRow) {
        if (rows > maxRows || seatsPerRow > maxSeatsPerRow) {
            throw new IllegalArgumentException(String.format("Values between %d and %d is expected for rows and values between %d and %d expected for seat per row", firstRowIndex, maxRows, firtRowNumber, maxSeatsPerRow));
        } else if (rows <= firstRowIndex || seatsPerRow <= firtRowNumber) {
            throw new IllegalArgumentException(String.format("Values between %d and %d is expected for rows and values between %d and %d expected for seat per row", firstRowIndex, maxRows, firtRowNumber, maxSeatsPerRow));
        } 
        this.maxSeatsPerRow = maxSeatsPerRow;
        this.maxRows = maxRows;
        this.lastRowNumber = seatsPerRow;
        this.lastRowIndex = rows;
    }


    /**
     * Initialize the seats for this showtime.
     * @return
     */
    Map<Seat, Boolean> initializeSeats() {
        Map<Seat, Boolean> seats = new HashMap<Seat, Boolean>();
        for (int rowIndex = getFirstRowIndex(); rowIndex <= this.getLastRowIndex(); rowIndex++) {
            for (int rowNumber = getFirtRowNumber(); rowNumber <= this.getLastRowNumber(); rowNumber++) {
                Seat seat = generateSeat(rowIndex, rowNumber);
                seats.put(seat, false);
            }
        }
        return seats;
    }

    /**
     * Get standardized seat number from row/seat values.
     * 
     * @param row    the theater row number (1-26)
     * @param number the seat position within row
     * @return formatted seat number string (e.g., "A1", "B2")
     * @throws IllegalStateException if row exceeds letter 'Z' (26 rows)
     */
    abstract Seat generateSeat(int row, int rowNumber);

    /**
     * Return the row and column from a given seat identifier
     *
     * @param seatId the seat identifier string
     * @return an int array where index 0 is the row index and index 1 is the seat (column) index,
     *         or null if the identifier is invalid.
     */
    abstract Seat parseSeatId(String seatId);

    /**
     * Validate that the rowIndex and rowNumber are within the bounds of this screen. 
     * 
     * @return true if seat is valid.
     * @throws IllegalArgumentException if seat id is invalid
     */
    boolean isWithinBounds(int rowIndex, int rowNumber) {
        if (rowIndex > this.getLastRowIndex() || rowNumber > this.getLastRowNumber()) {
            throw new IllegalArgumentException((INVALID_SEAT_MESSSAGE));
        } else if (rowIndex < this.getFirstRowIndex() || rowNumber < this.getFirtRowNumber()) {
            throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
        } 
        return true; 
    }
}
