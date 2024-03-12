package pages;

import input.UserClass;
import input.movieClass;

import java.util.ArrayList;

public class StaticValues {

    public StaticValues() {
    }

    // the current page
    private static String pageName;

    public static String getPageName() {
        return pageName;
    }

    public static void setPageName(final String pageName) {
        StaticValues.pageName = pageName;
    }

    // the current user
    private static UserClass currentUser;

    public static UserClass getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(final UserClass currentUser) {
        StaticValues.currentUser = currentUser;
    }

    // the current movie
    private static String currentMovie;

    public static String getCurrentMovie() {
        return currentMovie;
    }

    public static void setCurrentMovie(final String currentMovie) {
        StaticValues.currentMovie = currentMovie;
    }

    // result of filters
    private static ArrayList<movieClass> filteredMovies;

    public static ArrayList<movieClass> getFilteredMovies() {
        return filteredMovies;
    }

    public static void setFilteredMovies(final ArrayList<movieClass> filteredMovies) {
        StaticValues.filteredMovies = filteredMovies;
    }
}
