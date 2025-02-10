package com.gic.cinema.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Represents a movie screen with max 26 row and 50 seats per configuration and using seat ids of the format A1, A2 ...etc
 *
 * Constraints:
 * <ul>
 *   <li>Maximum number of rows: 26</li>
 *   <li>Maximum seats per row: 50</li>
 * </ul>
 * </p>
 *
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
class DefaultScreen extends Screen {
    private final static int MAX_SEATS_PER_ROW = 50;
    private final static int MAX_ROWS = 26;

    DefaultScreen(int totalRows, int seatsPerRow) {
        super(totalRows, seatsPerRow, MAX_ROWS, MAX_SEATS_PER_ROW, new DefaultScreenLayout(FIRST_ROW_INDEX, totalRows, FIRST_SEATPOS_INDEX, seatsPerRow));     
    }

    /**
     * Generate a seat with a seat numbers of the format A1, B2 ...etc.
     * 
     * @param row    the theater row number (1-26)
     * @param number the seat position within row
     * @return formatted seat number string (e.g., "A1", "B2")
     * @throws IllegalStateException if row exceeds letter 'Z' (26 rows)
     */
    @Override
    String getSeatId(@NonNull Seat seat) {
        int rowIndex = seat.getRowIndex();
        int seatPosIndex = seat.getSeatPosIndex();
        if(isWithinBounds(seat)) {
            return String.format("%c%d", (char)('A' + rowIndex - 1), seatPosIndex);
        }
        throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
    }

     @Override
    Seat parseSeatId(@NonNull String seatId) {
        if (seatId.isEmpty() || seatId.length() > 4) {
            throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
        }
        
        char rowChar = Character.toUpperCase(seatId.charAt(0));
        if (rowChar >= 'A' || rowChar <= 'Z') {
            int rowIndex = Integer.valueOf(rowChar - 'A' + 1);
            String seatPosStr = seatId.substring(1);
            try {
                int seatPosIndex = Integer.parseInt(seatPosStr);
                Seat seat = new Seat(rowIndex, seatPosIndex);
                if(isWithinBounds(seat)) {
                    return seat;
                }
                throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE, e);
            }
        }
        throw new IllegalArgumentException(INVALID_SEAT_MESSSAGE);
    }
}