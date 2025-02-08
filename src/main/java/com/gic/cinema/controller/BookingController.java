package com.gic.cinema.controller;

import java.util.Optional;
import java.util.Set;

import com.gic.cinema.model.Seat;
import com.gic.cinema.model.Theater;
import com.gic.cinema.model.Ticket;
import com.gic.cinema.view.BookingView;

public class BookingController {
    private final Theater theater;
    private final BookingView view;
    
    public BookingController(Theater theater, BookingView view) {
        this.theater = theater;
        this.view = view;
    }
    
    // Start processing the user input loop.
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            int option = view.displayMainMenu(theater.getShowtime().getMovieName(), theater.getShowtime().getNumRemainingSeats());
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
                    view.showMessage("\nInvalid choice. Please try again.");
            }
        }
    }
    
    // Handle the ticket booking flow.
    private void processBooking() {
        boolean isBookingFlowComplete = false;
        while (!isBookingFlowComplete) {
            Optional<Integer> numSeatsToBookOpt = view.promptNumberOfSeats();
            if (numSeatsToBookOpt.isEmpty()) {
                isBookingFlowComplete = true;
                continue;
            }
            int numSeatsToBook = numSeatsToBookOpt.get();
            if (numSeatsToBook <= 0) {
                view.showMessage("\nSorry, invalid entry!");
                return;
            } else if (numSeatsToBook > theater.getShowtime().getNumRemainingSeats()) {
                view.showMessage("\nSorry, there are only " + theater.getShowtime().getNumRemainingSeats() + " seats available.");
                return;
            }
            try {
                // Delegate seat selection to a separate strategy—this example assumes a SeatSelector exists.
                processSeatSelection(numSeatsToBook);
                isBookingFlowComplete = true;
            } catch (Exception e) {
                view.showMessage("\nError booking ticket: " + e.getMessage());
            }
        }
    }
    
    private void processSeatSelection(int numSeatsToBook) {
        Set<Seat> selectedSeats = SeatSelectionController.findNextAvailableSeatsForBooking(theater.getShowtime(), numSeatsToBook);
        
        String ticketId = theater.generateTicketID();
        view.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()));
        view.showMessage("\nBooking Id: %s confirmed." + ticketId);
        // Display the selected seats (optional, for demonstration purposes).
        
        boolean isSeatSelectionComplete = false;

        while (!isSeatSelectionComplete) {
            Optional<String> changeSeatSelectionOpt = view.promptChangeSeatSelection();
            if (changeSeatSelectionOpt.isEmpty()) {
                isSeatSelectionComplete = true;
                theater.confirmTicket(ticketId, selectedSeats);
                view.showMessage("\nBooking Id: " + ticketId + " confirmed.");
            } else {
                String startSeat = changeSeatSelectionOpt.get();
                selectedSeats = SeatSelectionController.findNextAvailableSeatsForBooking(theater.getShowtime(), numSeatsToBook, startSeat);
                view.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()));
                view.showMessage("\nBooking Id: %s confirmed." + ticketId);
            }
        }
    }

    // Handle checking a booking.
    private void processCheckBooking() {
        boolean isCheckBookingFlowComplete = false;
        while (!isCheckBookingFlowComplete) {
            Optional<String> bookingId = view.promptBookingId();
            if (bookingId.isEmpty()) {
                isCheckBookingFlowComplete = true;
                continue;
            }
            Optional<Ticket> ticketOpt = theater.getBookedTicket(bookingId.get());
            if (!ticketOpt.isPresent()) {
                view.showMessage("\nInvalid booking id. No booking found.");
            } else {
                Ticket ticket = ticketOpt.get();
                view.showMessage("\nBooking Id: " + ticket.getTicketId());
                view.showMessage("Selected Seats:" + ticket.getReservedSeats());
                // Here you could also call a dedicated layout printer if needed.
            }
        }
    }
}
