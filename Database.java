package pages;

import input.UserClass;
import input.movieClass;

import java.util.ArrayList;

public final class Database {

    private static Database instance = null;
    private Database() {
        this.moviesBefore = new ArrayList<>();
    }
    /** Singleton declaration*/
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private ArrayList<UserClass> registredUsers;
    private ArrayList<movieClass> movies;
    private ArrayList<movieClass> moviesBefore;

    public ArrayList<UserClass> getRegistredUsers() {
        return registredUsers;
    }

    public void setRegistredUsers(final
                                  ArrayList<UserClass> registredUsers) {
        this.registredUsers = registredUsers;
    }

    public ArrayList<movieClass> getMovies() {
        return movies;
    }

    public void setMovies(final
                          ArrayList<movieClass> movies) {
        this.movies = movies;
    }

    public ArrayList<movieClass> getMoviesBefore() {
        return moviesBefore;
    }

    public void setMoviesBefore(final
                                ArrayList<movieClass> moviesBefore) {
        this.moviesBefore = moviesBefore;
    }
}
