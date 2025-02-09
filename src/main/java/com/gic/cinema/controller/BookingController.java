package com.gic.cinema.controller;

import java.util.Optional;
import java.util.Set;

import com.gic.cinema.model.Seat;
import com.gic.cinema.model.Theater;
import com.gic.cinema.model.Ticket;
import com.gic.cinema.view.BookingView;
import com.gic.cinema.view.ScreenLayoutView;

public class BookingController {
    private final Theater theater;
    private final BookingView bookingView;
    private final ScreenLayoutView screenLayoutView;

    
    public BookingController(Theater theater, BookingView bookingView, ScreenLayoutView screenLayoutView) {
        this.theater = theater;
        this.bookingView = bookingView;
        this.screenLayoutView = screenLayoutView;
    }
    
    // Start processing the user input loop.
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            int option = bookingView.displayMainMenu(theater.getShowtime().getMovieName(), theater.getShowtime().getAvailableSeatCount());
            switch (option) {
                case 1:
                    processBooking();
                    break;
                case 2:
                    processCheckBooking();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    bookingView.showMessage("\nInvalid choice. Please try again.");
            }
        }
    }
    
    // Handle the ticket booking flow.
    private void processBooking() {
        boolean isBookingFlowComplete = false;
        while (!isBookingFlowComplete) {
            Optional<Integer> numSeatsToBookOpt = bookingView.promptNumberOfSeats();
            if (numSeatsToBookOpt.isEmpty()) {
                isBookingFlowComplete = true;
                continue;
            }
            int numSeatsToBook = numSeatsToBookOpt.get();
            if (numSeatsToBook <= 0) {
                bookingView.showMessage("\nSorry, invalid entry!");
                return;
            } else if (numSeatsToBook > theater.getShowtime().getAvailableSeatCount()) {
                bookingView.showMessage("\nSorry, there are only " + theater.getShowtime().getAvailableSeatCount() + " seats available.");
                return;
            }
            try {
                // Delegate seat selection to a separate strategy—this example assumes a SeatSelector exists.
                processSeatSelection(numSeatsToBook);
                isBookingFlowComplete = true;
            } catch (Exception e) {
                bookingView.showMessage("\nError booking ticket: " + e.getMessage());
            }
        }
    }
    
    private void processSeatSelection(int numSeatsToBook) {
        Set<Seat> selectedSeats = theater.getShowtime().getAvailableSeats(numSeatsToBook);
        
        String ticketId = theater.generateTicketID();
        bookingView.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()));
        bookingView.showMessage("\nBooking Id: %s confirmed." + ticketId);
        // Display the selected seats (optional, for demonstration purposes).
        screenLayoutView.printScreenLayout(theater.getShowtime(), selectedSeats);

        boolean isSeatSelectionComplete = false;

        while (!isSeatSelectionComplete) {
            Optional<String> changeSeatSelectionOpt = bookingView.promptChangeSeatSelection();
            if (changeSeatSelectionOpt.isEmpty()) {
                isSeatSelectionComplete = true;
                theater.confirmBooking(ticketId, selectedSeats);
                bookingView.showMessage("\nBooking Id: " + ticketId + " confirmed.");
            } else {
                String startSeat = changeSeatSelectionOpt.get();
                selectedSeats = theater.getShowtime().getAvailableSeats(startSeat, numSeatsToBook);
                // Display the selected seats

                bookingView.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()));
                bookingView.showMessage("\nBooking Id: %s confirmed." + ticketId);
            }
        }
    }

    // Handle checking a booking.
    private void processCheckBooking() {
        boolean isCheckBookingFlowComplete = false;
        while (!isCheckBookingFlowComplete) {
            Optional<String> bookingId = bookingView.promptBookingId();
            if (bookingId.isEmpty()) {
                isCheckBookingFlowComplete = true;
                continue;
            }
            Optional<Ticket> ticketOpt = theater.getBookedTicket(bookingId.get());
            if (!ticketOpt.isPresent()) {
                bookingView.showMessage("\nInvalid booking id. No booking found. Try again!.");
            } else {
                Ticket ticket = ticketOpt.get();
                bookingView.showMessage("\nBooking Id: " + ticket.getTicketId());
                bookingView.showMessage("Selected Seats:" + ticket.getReservedSeats());
                // Here you could also call a dedicated layout printer if needed.
            }
        }
    }
}
