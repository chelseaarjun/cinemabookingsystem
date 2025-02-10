package com.gic.cinema.controller;

import java.util.Optional;
import java.util.Set;

import com.gic.cinema.model.Seat;
import com.gic.cinema.model.Theater;
import com.gic.cinema.model.Ticket;
import com.gic.cinema.view.BookingView;

public class BookingController {
    private final Theater theater;
    private final BookingView bookingView;
    
    public BookingController(Theater theater, BookingView bookingView) {
        this.theater = theater;
        this.bookingView = bookingView;
    }
    
    // Start processing the user input loop.
    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            Optional<Integer> option = bookingView.displayMainMenu(theater.getShowtime().getMovieName(), theater.getShowtime().getAvailableSeatCount());
            if (!option.isPresent()) {
                bookingView.showMessage("\nInvalid choice. Please try again.");
                continue;
            }
            switch (option.get()) {
                case 1:
                    processBooking();
                    break;
                case 2:
                    processCheckBooking();
                    break;
                case 3:
                    bookingView.showMessage("\nThank you for using GIC Cinema system. Bye!");
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
                continue;
            } else if (numSeatsToBook > theater.getShowtime().getAvailableSeatCount()) {
                bookingView.showMessage("\nSorry, there are only " + theater.getShowtime().getAvailableSeatCount() + " seats available.");
                continue;
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
        Set<Seat> selectedSeats = theater.getShowtime().getNextAvailableSeats(numSeatsToBook);
        
        Ticket ticket = theater.generateTicket(selectedSeats);
        bookingView.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()), false);
        bookingView.showTicket(ticket);

        boolean isSeatSelectionComplete = false;

        while (!isSeatSelectionComplete) {
            Optional<String> changeSeatSelectionOpt = bookingView.promptChangeSeatSelection();
            if (!changeSeatSelectionOpt.isPresent()) {
                bookingView.showMessage("\nInvalid entry. Try again!.");
                continue;
            }
            if (changeSeatSelectionOpt.get().isBlank()) {
                isSeatSelectionComplete = true;
                theater.confirmTicket(ticket);
                bookingView.showMessage("Booking Id: " + ticket.getTicketId() + " confirmed.");
            } else {
                String startSeat = changeSeatSelectionOpt.get();
                selectedSeats = theater.getShowtime().getNextAvailableSeats(numSeatsToBook, startSeat);
                bookingView.showMessage(String.format("\nSuccessfully reserved %d %s tickets.", numSeatsToBook, theater.getShowtime().getMovieName()));
                bookingView.showTicket(ticket);
            }
        }
    }

    // Handle checking a booking.
    private void processCheckBooking() {
        boolean isCheckBookingFlowComplete = false;
        while (!isCheckBookingFlowComplete) {
            Optional<String> bookingId = bookingView.promptBookingId();
            if (!bookingId.isPresent()) {
                bookingView.showMessage("\nInvalid booking id. No booking found. Try again!.");
                continue;
            }
            if ( bookingId.get().isBlank()) {
                isCheckBookingFlowComplete = true;
                continue;
            }
            Optional<Ticket> ticketOpt = theater.getBookedTicket(bookingId.get());
            if (!ticketOpt.isPresent()) {
                bookingView.showMessage("\nInvalid booking id. No booking found. Try again!.");
            } else {
                Ticket ticket = ticketOpt.get();
                bookingView.showTicket(ticket);
            }
        }
    }
}