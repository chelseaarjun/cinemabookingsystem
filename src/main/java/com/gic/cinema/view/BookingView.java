package com.gic.cinema.view;

import java.util.Optional;
import java.util.Scanner;

public class BookingView {
    private final Scanner scanner;
    
    public BookingView(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // Display a welcome message including the movie title and available seats.
    public void displayFirstMsg() {
        System.out.println("\nPlease define movie title and seating map in [Title] [Row] [SeatsPerRow] format");
    }
    
    // Display the main menu and return the selected option.
    public int displayMainMenu(String movieName, long availableSeats) {
        System.out.println("\nWelcome to Cinema Booking System for " + movieName +
                           " (" + availableSeats + " seats available).");
        System.out.println("Options:");
        System.out.println("1. Book a ticket");
        System.out.println("2. Check Booking");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");
        return scanner.nextInt();
    }
    
    // Prompt the user for the number of seats to book.
    public Optional<Integer> promptNumberOfSeats() {
        System.out.print("\nEnter number of seats to book, or enter blank to go back to main menu");
        String input = scanner.nextLine().trim();
        if(input.isBlank()){
            return Optional.empty();
        } else {
            return Optional.of(Integer.parseInt(input));
        }
    }

    // Prompt the user for changing seats.
    public Optional<String> promptChangeSeatSelection() {
        System.out.print("\nEnter blank to accept seat selection, or enter new seating position");
        return Optional.of(scanner.nextLine().trim());
    }
    
    // Prompt the user for a booking id to see their seats.
    public Optional<String> promptBookingId() {
        System.out.print("\nEnter booking id, or enter blank to go back to main menu");
        return Optional.of(scanner.nextLine().trim());
    }
    
    // Display a simple message.
    public void showMessage(String message) {
        System.out.println(message);
    }
}
