package com.gic.cinema.app;

import java.util.Scanner;

import com.gic.cinema.model.Theater;
import com.gic.cinema.controller.BookingController;
import com.gic.cinema.view.BookingView;

public class CinemaBookingApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        
            BookingView view = new BookingView(scanner);

            boolean hasParsetInput = false;
            while (!hasParsetInput) {
                // Get movie and screen settings from the user.
                view.displayFirstMsg();
            
                String[] input = scanner.nextLine().split(" ");
                if (input.length != 3) {
                    view.showMessage("Invalid input. Please enter the movie title and screen settings.");
                    continue;
                }
                String movieTitle = input[0];
                int totalRows;
                int seatsPerRow;
                try {
                    totalRows = Integer.parseInt(input[1]);
                    seatsPerRow = Integer.parseInt(input[2]);
                } catch (NumberFormatException e) {
                    view.showMessage("Invalid input. Movie [Title] is expected to a single word. [Row] and [SeatsPerRow] are expected to be integers.");
                    continue;
                }
                
                try {
                    // Create the business logic model (theater, showtime, etc.)
                    Theater theater = new Theater(movieTitle, totalRows, seatsPerRow);
                    // Create a controller, injecting the model and the view.
                    BookingController controller = new BookingController(theater, view);

                    hasParsetInput = true;
                    // Start the application loop.
                    controller.start();
                } catch (IllegalArgumentException e) {
                    view.showMessage(e.getMessage());
                    continue;
                }
            }          
            scanner.close();
        }
    }
}