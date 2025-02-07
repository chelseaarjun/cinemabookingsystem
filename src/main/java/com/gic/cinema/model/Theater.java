package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents a cinema theater for a specific movie.
 * <p>
 * The Theater class encapsulates the seating arrangement and ticket management
 * for a movie screening. It creates a list of seats based on the provided number
 * of rows and seats per row. For bookings, it maintains a mapping of ticket IDs to
 * Ticket objects.
 * </p>
 * <p>
 * Constraints:
 * <ul>
 *   <li>Maximum rows: 26 (rows are labeled A-Z)</li>
 *   <li>Maximum seats per row: 50</li>
 *   <li>The movie name must not be null.</li>
 * </ul>
 * </p>
 *
 * @version 1.0
 * @see Seat
 * @see Ticket
 */
@Getter
@ToString
public class Theater {
    private final static String SCREEN_ID = "1";
    private final Map<String, Ticket> tickets;
    private final Screen screen;
    private int ticketCounter;

    public Theater(@NonNull String movieName, int rows, int seatsPerRow) {
        this.screen = new Screen(SCREEN_ID, movieName, rows, seatsPerRow);
        this.tickets = new HashMap<>();
        this.ticketCounter = 0;
    }
    
    public List<Seat> getFreeSeats(int numSeats) {
        return new ArrayList<Seat>();
    }

    public Ticket confirmSeatSelection(List<Seat> seats) {
        return null;
    }

    public Ticket checkBooking(String ticketID) {
        return null;
    }

    public void displaySeatingArrangement() {
    }
}
