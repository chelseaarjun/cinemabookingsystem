package com.gic.cinema.view;

import java.util.Optional;
import java.util.Scanner;

import com.gic.cinema.model.Ticket;

public class BookingView {
    private static final char RESERVED_SEAT_SYMBOL = '#';
    private static final char AVAILABLE_SEAT_SYMBOL = '.';
    private static final char SELECTED_SEAT_SYMBOL = 'o';
    private final Scanner scanner;
    
    public BookingView(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // Display a welcome message including the movie title and available seats.
    public void displayFirstMsg() {
        System.out.println("\nPlease define movie title and seating map in [Title] [Row] [SeatsPerRow] format");
    }
    
    // Display the main menu and return the selected option.
    public Optional<Integer> displayMainMenu(String movieName, long availableSeats) {
        System.out.println("\nWelcome to Cinema Booking System for " + movieName +
                           " (" + availableSeats + " seats available).");
        System.out.println("Options:");
        System.out.println("1. Book a ticket");
        System.out.println("2. Check Booking");
        System.out.println("3. Exit");
        System.out.println("Please enter your choice:");
        try {
            return Optional.of(Integer.parseInt(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    // Prompt the user for the number of seats to book.
    public Optional<Integer> promptNumberOfSeats() {
        System.out.println("\nEnter number of seats to book, or enter blank to go back to main menu:");
        try{
            return Optional.of(Integer.parseInt(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // Prompt the user for changing seats.
    public Optional<String> promptChangeSeatSelection() {
        System.out.println("\nEnter blank to accept seat selection, or enter new seating position:");
        return Optional.of(scanner.nextLine().trim());
    }
    
    // Prompt the user for a booking id to see their seats.
    public Optional<String> promptBookingId() {
        System.out.println("\nEnter booking id, or enter blank to go back to main menu:");
        return Optional.of(scanner.nextLine().trim());
    }
    
    // Display a simple message.
    public void showMessage(String message, boolean withNewLine) {
        if(withNewLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }

    // Display a simple message.
    public void showMessage(String message) {
        showMessage(message, true);
        
    }

    // Display a simple message.
    /**
     * @param ticket
     */
    public void showTicket(Ticket ticket) {
        showMessage("\nBooking Id: " + ticket.getTicketId());
        showMessage("Selected Seats:" + ticket.getSeats());
        System.out.println("=====================================");
        System.out.println(RESERVED_SEAT_SYMBOL);
        System.out.println(AVAILABLE_SEAT_SYMBOL);
        System.out.println(SELECTED_SEAT_SYMBOL);
        //show layout
    }
}
