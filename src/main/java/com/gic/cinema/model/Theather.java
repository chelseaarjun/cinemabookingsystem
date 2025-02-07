package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Theather {
    private final int MAX_SEATS_PER_ROW = 50;
    private final int MAX_ROWS = 26;

    private final List<Seat> allSeats;
    private final Map<String, Ticket> tickets;
    private final String movieName;
    private int ticketCounter;

    public Theather(String movieName, int rows, int seatsPerRow) {
        if (rows > MAX_ROWS || seatsPerRow > MAX_SEATS_PER_ROW) {
            throw new IllegalArgumentException("The maximum number of rows allowed is 26 and maximum number of seats allowed per row allowed is 50");
        }
        this.movieName = movieName;
        this.allSeats = initializeSeats(rows, seatsPerRow);
        this.tickets = new HashMap<>();
        this.ticketCounter = 0;
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
