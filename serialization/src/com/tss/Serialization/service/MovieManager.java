package com.tss.Serialization.service;

import com.tss.Serialization.exceptions.MovieNotFoundException;
import com.tss.Serialization.exceptions.MoviePersistenceException;
import com.tss.Serialization.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MovieManager {
    private static final String path = "movies.ser";
    private List<Movie> movies;

    public MovieManager() throws MoviePersistenceException {
        movies = new ArrayList<>();
        loadMovies();
    }

    public void loadMovies() throws MoviePersistenceException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("No saved movies found. Starting fresh.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            movies = (ArrayList<Movie>) ois.readObject();
            System.out.println("Movies loaded successfully");

            int maxId = 0;
            for (Movie m : movies) {
                if (m.getId() > maxId) maxId = m.getId();
            }
            Movie.idCounter = maxId + 1;

        } catch (IOException | ClassNotFoundException e) {
            throw new MoviePersistenceException("Failed to load movies");
        }
    }

    public void saveMovies() throws MoviePersistenceException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(movies);
            System.out.println("Movies saved successfully");
        } catch (IOException e) {
            throw new MoviePersistenceException("Failed to save movies");
        }
    }

    public void clearMovies() throws MoviePersistenceException {
        movies.clear();
        saveMovies();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Movie getMovieById(int movieId) throws MovieNotFoundException {
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                return movie;
            }
        }
        throw new MovieNotFoundException("Movie with ID " + movieId + " not found");
    }
}