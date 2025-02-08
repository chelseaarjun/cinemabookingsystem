package com.gic.cinema.model;

import lombok.Getter;
import lombok.ToString;

/**
 * Represents a movie screen with its seating arrangement in a cinema theater.
 * <p>
 * The Screen class encapsulates the seating layout for a particular movie screen,
 * supporting flexible management when adding more screens. It creates and holds a
 * seating map based on the given number of rows and seats per row.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 *   <li>Maximum number of rows: 26</li>
 *   <li>Maximum seats per row: 50</li>
 * </ul>
 * </p>
 *
 */
@Getter
@ToString
public class Screen {
    private final int MAX_SEATS_PER_ROW = 50;
    private final int MAX_ROWS = 26;
    private final int seatsPerRow;
    private final int rows;

    /**
     * Constructs a new Screen instance with the specified movie name and seating dimensions.
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
    Screen(int rows, int seatsPerRow) {
        if (rows > MAX_ROWS || seatsPerRow > MAX_SEATS_PER_ROW) {
            throw new IllegalArgumentException("The maximum number of rows allowed is 26 and maximum number of seats allowed per row allowed is 50");
        } else if (rows <=0 || seatsPerRow <=0) {
            throw new IllegalArgumentException("Values between 1 and 26 is expected for rows and values between 1 and 50 expected for seat per row");
        }        
        this.seatsPerRow = seatsPerRow;
        this.rows = rows;
    }
}
