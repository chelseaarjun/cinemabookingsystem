package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
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

    private final List<Seat> allSeats;
    private final String movieName;
    private final String screenId;

    /**
     * Constructs a new Screen instance with the specified movie name and seating dimensions.
     * <p>
     * This constructor initializes the seating arrangement based on the provided
     * number of rows and seats per row. It validates that the number of rows does not exceed
     * {@code MAX_ROWS} and the number of seats per row does not exceed {@code MAX_SEATS_PER_ROW}. If these conditions are not met,
     * an {@link IllegalArgumentException} is thrown.
     * </p>
     * 
     * @param screenId    the identifier for this movie screen; must not be {@code null}.
     * @param movieName   the title of the movie displayed on this screen; must not be {@code null}.
     * @param rows        the number of rows in the screen.
     * @param seatsPerRow the number of seats in each row.
     * @throws IllegalArgumentException if {@code rows} is greater than {@code MAX_ROWS} or
     *                                  {@code seatsPerRow} is greater than {@code MAX_SEATS_PER_ROW}.
     */
    public Screen(@NonNull String screenId, @NonNull String movieName, int rows, int seatsPerRow) {
        if (rows > MAX_ROWS || seatsPerRow > MAX_SEATS_PER_ROW) {
            throw new IllegalArgumentException("The maximum number of rows allowed is 26 and maximum number of seats allowed per row allowed is 50");
        }
        this.movieName = movieName;
        this.allSeats = initializeSeats(rows, seatsPerRow);
        this.screenId = screenId;
    }
    
    private List<Seat> initializeSeats(int rows, int seatsPerRow) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(row, seatNum));
            }
        }
        return seats;
    }
}
