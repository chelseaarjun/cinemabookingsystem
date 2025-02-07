package com.gic.cinema.model;

import lombok.Getter;
import lombok.ToString;

/**
 * Represents a seat in a theater auditorium with row and number identification.
 * <p>
 * Seats are automatically assigned a human-readable seat number in the format 
 * {@code [RowLetter][Number]} (e.g., "A1" or "Z50") based on theater row/seat 
 * conventions. Row letters can range from A-Z (1-26 rows) and seat numbers start at 1.
 * <p>
 * Seats are not reserved by default and can be marked as reserved.
 */
@Getter
@ToString
public class Seat {
    private final int row;
    private final int number;
    private final String seatNumber;  
    private boolean isReserved;

    /**
     * Constructs a seat with specified row and number.
     * 
     * @param row  the theater row number (1-26)
     * @param number the seat position within row (≥1)
     * @throws IllegalStateException if row exceeds 26 (maximum letter 'Z')
     * @see #generateSeatNumber(int, int)
     */
    public Seat(int row, int number) {
        this.seatNumber = generateSeatNumber(row, number);
        this.row = row;
        this.number = number;
        this.isReserved = false;
    }

      /**
     * Generates standardized seat number from row/seat values.
     * 
     * @param row    the theater row number (1-26)
     * @param number the seat position within row
     * @return formatted seat number string (e.g., "A1", "B2")
     * @throws IllegalStateException if row exceeds letter 'Z' (26 rows)
     */
    private String generateSeatNumber(int row, int number) {
        if (row < 1 || row > 26) {
            throw new IllegalStateException("Can only generate seat numbers for upto 26 rows");
        } 
        return String.format("%c%d", (char)('A' + row - 1), number);
    }
}