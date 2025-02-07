package com.gic.cinema.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Showtime {
    private final String movieName;
    private final Screen screen;
    private final Map<String, Seat> seats;

    public Showtime(String movieName, Screen screen) {
        this.movieName = movieName;
        this.screen = screen;
        this.seats = initializeSeats();
    }

    public List<String> getNextAvailableSeats(int count) {
        return  seats.values().stream()
            .filter(s -> !s.isReserved())
            .map(s -> s.getSeatNumber())
            .collect(Collectors.toList());
    }

    public List<String> reserveSeats(List<String> seatNumbers) {
        return new ArrayList<String>();
    }

    public Map<String, Seat> initializeSeats() {
        Map<String, Seat> seats = new HashMap<String, Seat>();
        for (int row = 1; row <= screen.getRows(); row++) {
            for (int seatNum = 1; seatNum <= screen.getSeatsPerRow(); seatNum++) {
                Seat seat = new Seat(row, seatNum);
                seats.put(seat.getSeatNumber(), seat);
            }
        }
        return seats;
    }
}