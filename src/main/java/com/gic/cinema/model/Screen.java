package com.gic.cinema.model;

import java.util.HashSet;
import java.util.Set;

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
    final static int FIRST_ROW_INDEX = 1;
    final static int FIRST_SEATPOS_INDEX = 1;
    private final int maxSeatsPerRow;
    private final int maxRows;
    private final int seatsPerRow;
    private final int totalRows;
    private final ScreenLayout screenLayout;

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
    Screen(int totalRows, int seatsPerRow, int maxRows, int maxSeatsPerRow, ScreenLayout screenLayout) {
        if (totalRows > maxRows || seatsPerRow > maxSeatsPerRow) {
            throw new IllegalArgumentException(String.format("Values between %d and %d is expected for rows and values between %d and %d expected for seat per row", FIRST_ROW_INDEX, maxRows, FIRST_SEATPOS_INDEX, maxSeatsPerRow));
        } else if (totalRows < FIRST_ROW_INDEX || seatsPerRow < FIRST_SEATPOS_INDEX) {
            throw new IllegalArgumentException(String.format("Values between %d and %d is expected for rows and values between %d and %d expected for seat per row", FIRST_ROW_INDEX, maxRows, FIRST_SEATPOS_INDEX, maxSeatsPerRow));
        } 
        this.maxSeatsPerRow = maxSeatsPerRow;
        this.maxRows = maxRows;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.screenLayout = screenLayout;
    }

    /**
    * Initialize the seats for this screen.
    * @return
    */
    Set<Seat> initializeSeats() {
        final Set<Seat> seats = new HashSet<Seat>();
        for (int rowIndex = FIRST_ROW_INDEX; rowIndex <= totalRows; rowIndex++) {
            for (int seatPos = FIRST_SEATPOS_INDEX; seatPos <= seatsPerRow; seatPos++) {
                Seat seat = new Seat(rowIndex, seatPos);
                seats.add(seat);
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
    abstract String getSeatId(Seat seat);

    /**
     * Return the row and column from a given seat identifier
     *
     * @param seatId the seat identifier string
     * @return an int array where index 0 is the row index and index 1 is the seat (column) index,
     *         or null if the identifier is invalid.
     */
    abstract Seat parseSeatId(String seatId);

    /**
     * Validate that the row index and seat position are within the bounds of this screen. 
     * 
     * @return true if seat is valid.
     * @throws IllegalArgumentException if seat id is invalid
     */
    boolean isWithinBounds(Seat seat) {
        int rowIndex = seat.getRowIndex();
        int seatPosIndex = seat.getSeatPosIndex();
        if (rowIndex > totalRows || seatPosIndex > seatsPerRow) {
            throw new IllegalArgumentException((INVALID_SEAT_MESSSAGE));
        } else if (rowIndex < FIRST_ROW_INDEX || seatPosIndex < FIRST_SEATPOS_INDEX) {
            throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
        } 
        return true; 
    }
}
