package com.gic.cinema.app;

import java.util.Scanner;

import com.gic.cinema.model.Theater;
import com.gic.cinema.controller.BookingController;
import com.gic.cinema.view.BookingView;
import com.gic.cinema.view.ScreenLayoutView;

public class CinemaBookingApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        
            BookingView view = new BookingView(scanner);
            ScreenLayoutView screenLayoutView = new ScreenLayoutView();

            // Get movie and screen settings from the user.
            view.displayFirstMsg();
        
            String movieTitle = scanner.next();
            int totalRows = scanner.nextInt();
            int seatsPerRow = scanner.nextInt();
            
            // Create the business logic model (theater, showtime, etc.)
            Theater theater = new Theater(movieTitle, totalRows, seatsPerRow);
                        
            // Create a controller, injecting the model and the view.
            BookingController controller = new BookingController(theater, view, screenLayoutView);
            
            // Start the application loop.
            controller.start();
            
            scanner.close();

        }
    }
}