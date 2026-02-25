package com.tss.Serialization.service;

import com.tss.Serialization.service.MovieManager;
import com.tss.Serialization.model.Movie;
import com.tss.Serialization.exceptions.MovieNotFoundException;
import com.tss.Serialization.exceptions.MoviePersistenceException;

import java.util.Scanner;

public class MovieController {

    private MovieManager movieManager;
    private Scanner scanner = new Scanner(System.in);

    public MovieController() {
        try {
            movieManager = new MovieManager();
        } catch (MoviePersistenceException e) {
            System.out.println("Error loading movies: " + e.getMessage());
            movieManager = new MovieManager();
        }
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addMovie();
                    case 2 -> displayMovies();
                    case 3 -> clearAllMovies();
                    case 4 -> getMovieById();
                    case 5 -> updateMovieById();
                    case 0 -> exitApp();
                    default -> System.out.println("Invalid choice!");
                }
            } catch (MoviePersistenceException | MovieNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void displayMenu() {
        System.out.println("\n==== Simple Movies App ====");
        System.out.println("1. Add Movie");
        System.out.println("2. Display Movies");
        System.out.println("3. Clear All Movies");
        System.out.println("4. Get Movie By ID");
        System.out.println("5. Update Movie By ID");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private void addMovie() throws MoviePersistenceException {
        if (movieManager.getMovies().size() >= 5) {
            System.out.println("Cannot add more than 5 movies.");
            return;
        }

        System.out.print("Enter Movie Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Release Year: ");
        int year = scanner.nextInt();

        Movie movie = new Movie(name, year, genre);
        movieManager.addMovie(movie);
        movieManager.saveMovies();
        System.out.println("Movie added successfully.");
    }

    private void displayMovies() {
        if (movieManager.getMovies().isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
        for (Movie movie : movieManager.getMovies()) {
            System.out.println(movie);
        }
    }

    private void clearAllMovies() throws MoviePersistenceException {
        movieManager.clearMovies();
        Movie.idCounter = 1;
        System.out.println("All movies cleared.");
    }

    private void getMovieById() throws MovieNotFoundException {
        System.out.print("Enter Movie ID: ");
        int id = scanner.nextInt();
        Movie movie = movieManager.getMovieById(id);
        System.out.println(movie);
    }

    private void updateMovieById() throws MoviePersistenceException, MovieNotFoundException {
        System.out.print("Enter Movie ID to update (0 to exit): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id == 0) return;

        Movie movie = movieManager.getMovieById(id);
        System.out.println("Current Movie Details:");
        System.out.println(movie);

        System.out.print("Enter New Movie Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter New Release Year: ");
        int year = scanner.nextInt();

        movie.setName(name);
        movie.setGenre(genre);
        movie.setYear(year);
        movieManager.saveMovies();
        System.out.println("Movie updated successfully.");
    }

    private void exitApp() throws MoviePersistenceException {
        movieManager.saveMovies();
        System.out.println("Exiting application...");
    }
}