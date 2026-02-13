package com.tss.Serialization;

import java.util.Scanner;
public class MovieController {

    private MovieManager movieManager;
    private Scanner scanner = new Scanner(System.in);

    public MovieController() {
        movieManager = new MovieManager();
        movieManager.loadMovies();
    }
    public void start() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    displayMovies();
                    break;
                case 3:
                    clearAllMovies();
                    break;
                case 4:
                    while (true) {
                        System.out.print("Enter movie id (0 to exit): ");
                        int id = scanner.nextInt();
                        if (id == 0) break;
                        Movie movie = movieManager.getMovieById(id);
                        if (movie != null) {
                            System.out.println(movie);
                            break;
                        } else {
                            System.out.println("Movie not found.");
                        }
                    }
                    break;
                case 5:
                    updateMovieById();
                    break;
                case 0:
                    movieManager.saveMovies();
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice!");
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

    private void addMovie() {
        if (movieManager.getmovies().size() >= 5) {
            System.out.println("Cannot add more than 5 movies.");
            return;
        }

        System.out.print("Enter Movie Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter Release Year: ");
        int year = scanner.nextInt();

        Movie movie = new Movie(name,year,genre);
        movieManager.addMovie(movie);
        movieManager.saveMovies();
        System.out.println("Movie added successfully.");
    }

    private void displayMovies() {
        if (movieManager.getmovies().isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
        for (Movie movie : movieManager.getmovies()) {
            System.out.println(movie);
        }
    }

    private void clearAllMovies() {
        movieManager.clearMovies();
        movieManager.saveMovies();
        Movie.idCounter =1;
        System.out.println("All movies cleared.");
    }
    private void updateMovieById() {
        System.out.print("Enter Movie ID to update (0 to exit): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id == 0) return;

        Movie movie = movieManager.getMovieById(id);

        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

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

}
