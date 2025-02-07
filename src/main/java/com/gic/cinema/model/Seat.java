package com.gic.cinema.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Seat {
    private final int row;
    private final int number;
    private final String seatNumber;  // e.g., "A1", "B2"
    private boolean isReserved;

    public Seat(int row, int number) {
        this.seatNumber = generateSeatNumber(row, number);
        this.row = row;
        this.number = number;
    }

    String generateSeatNumber(int row, int number) {
        if (row < 1 || row > 26) {
            throw new IllegalStateException("Can only generate seat numbers for upto 26 rows");
        } 
        return String.format("%c%d", (char)('A' + row - 1), number);
    }
}
