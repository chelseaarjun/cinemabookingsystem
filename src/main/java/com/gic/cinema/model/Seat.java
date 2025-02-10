package com.gic.cinema.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a seat with a seat number. 
 * <p>
 * Seats are automatically assigned a human-readable seat number in the format 
 * {@code [RowLetter][Number]} (e.g., "A1" or "Z50") based on theater row/seat 
 * conventions. Row letters can range from A-Z (1-26 rows) and seat numbers start at 1.
 * <p>
 * Seats are not reserved by default and can be marked as reserved.
 */
@Getter
@EqualsAndHashCode
@ToString
public class Seat {
    private final int rowIndex;
    private final int seatPosIndex;

    /**
     * Constructs a seat with specified row, number and isReserved status
     * 
     * @param rowIndex  the theater row number (1-26)
     * @param seatPos the seat position within row (≥1)
     * @param number reserved true or false
     * @throws IllegalStateException if row exceeds 26 (maximum letter 'Z') or is less than 1
    */
    Seat(int rowIndex, int seatPosIndex) {
        this.rowIndex = rowIndex;
        this.seatPosIndex = seatPosIndex;
    }
}