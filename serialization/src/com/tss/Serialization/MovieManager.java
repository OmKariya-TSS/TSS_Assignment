package com.tss.Serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private static String path = "movies.ser";
    private List<Movie> movies;
    public MovieManager() {
        movies= new ArrayList<>();
        loadMovies();
    }

    public void loadMovies(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            movies = (ArrayList<Movie>) ois.readObject();
            System.out.println("movies loaded successsfully");
            int maxId = 0;
            for (Movie m : movies) {
                if (m.getId() > maxId) maxId = m.getId();
            }
            Movie.idCounter = maxId + 1;
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException | ClassNotFoundException e ){
            e.printStackTrace();
        }
    }
    public void saveMovies(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(movies);
            System.out.println("movies saved successfully");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void clearMovies(){
        movies.clear();
        saveMovies();
    }
    public List<Movie> getmovies(){
        return movies;
    }
    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public Movie getMovieById(int movieId) {
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                return movie;
            }
        }
        return null;
    }
}
