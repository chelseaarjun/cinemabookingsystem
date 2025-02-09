package com.gic.cinema.view;

import java.util.Set;

import com.gic.cinema.model.Seat;
import com.gic.cinema.model.Showtime;

public class ScreenLayoutView {
    private static final char RESERVED_SEAT_SYMBOL = '#';
    private static final char AVAILABLE_SEAT_SYMBOL = '.';
    private static final char SELECTED_SEAT_SYMBOL = 'o';

    public void printScreenLayout(Showtime showtime, Set<Seat> selectedSeats) {
        showtime.getScreenLayout();
        System.out.println("=====================================");
        System.out.println(RESERVED_SEAT_SYMBOL);
        System.out.println(AVAILABLE_SEAT_SYMBOL);
        System.out.println(SELECTED_SEAT_SYMBOL);
    }
}
