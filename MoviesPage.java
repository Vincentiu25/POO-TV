package pages;

import input.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public final class MoviesPage extends Page {

    public MoviesPage() {
    }
    /** function to search for a movie*/
    public void search(final Database dataBase, final ArrayNode output,
                       final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<movieClass> allowedMovies = new ArrayList<>();
        // get all the allowed movies
        for (movieClass movies : dataBase.getMovies()) {
            String country = StaticValues.getCurrentUser().
                    getCredentials().getCountry();
            ArrayList<String> banned = movies.getCountriesBanned();

            int checkIfBanned = 1;

            for (int i = 0; i < banned.size(); i++) {
                if (banned.get(i).equals(country)) {
                    checkIfBanned = 0;
                }
            }
            if (checkIfBanned == 1) {
                movieClass aux = new movieClass(movies, 1);
                allowedMovies.add(aux);
            }
        }

        String startsWith = actionsArr.getStartsWith();
        ArrayList<movieClass> matchingMovies = new ArrayList<>();

        // search the movie in the allowed movies
        for (movieClass movies : allowedMovies) {
            if (movies.getName().startsWith(startsWith)) {
                movieClass aux = new movieClass(movies, 1);
                matchingMovies.add(aux);
            }
        }

        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList",
                matchingMovies.toArray());
        node.putPOJO("currentUser",
                StaticValues.getCurrentUser());
        output.add(node);
    }
    /** function to filter movies*/
    public void filter(final Database dataBase, final ArrayNode output,
                       final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();

        //get the allowed movies
        ArrayList<movieClass> allowedMovies = new ArrayList<>();
        for (movieClass movies : dataBase.getMovies()) {
            String country = StaticValues.getCurrentUser().
                    getCredentials().getCountry();
            ArrayList<String> banned = movies.getCountriesBanned();

            int checkIfBanned = 1;

            for (int i = 0; i < banned.size(); i++) {
                if (banned.get(i).equals(country)) {
                    checkIfBanned = 0;
                }
            }
            if (checkIfBanned == 1) {
                movieClass aux = new movieClass(movies, 1);
                allowedMovies.add(aux);
            }
        }

        Filters filters = new Filters();
        ArrayList filteredMovies = new ArrayList<>();
        // if there is at least one allowed movie, apply filters
        if (allowedMovies.size() != 0) {
            filteredMovies = filters.filter(actionsArr.getFilters(),
                    allowedMovies);
        }


        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList",
                filteredMovies.toArray());
        node.putPOJO("currentUser",
                StaticValues.getCurrentUser());
        output.add(node);

    }
    /** function to show all allowed movies*/
    public void showMovies(final Database dataBase,
                           final ArrayNode output) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<movieClass> allowedMovies = new ArrayList<>();
        // get the allowed movies
        for (movieClass movies : dataBase.getMovies()) {
            String country = StaticValues.getCurrentUser().
                    getCredentials().getCountry();
            ArrayList<String> banned = movies.getCountriesBanned();

            int checkIfBanned = 1;

            for (int i = 0; i < banned.size(); i++) {
                if (banned.get(i).equals(country)) {
                    checkIfBanned = 0;
                }
            }
            if (checkIfBanned == 1) {
                movieClass aux = new movieClass(movies, 1);
               allowedMovies.add(aux);
            }
        }
        ObjectNode node2 = objectMapper.createObjectNode();
        UserClass newUser = new UserClass(StaticValues.getCurrentUser());
        node2.putPOJO("error", null);
        node2.putPOJO("currentMoviesList",
                allowedMovies.toArray());
        node2.putPOJO("currentUser", newUser);
        output.add(node2);
    }
    /** function to show details of a movie*/
    public void details(final Database dataBase, final ArrayNode output,
                        final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<movieClass> allowedMovies = new ArrayList<>();
        // get the allowed movie
        for (movieClass movies : dataBase.getMovies()) {
            String country = StaticValues.getCurrentUser().
                    getCredentials().getCountry();
            ArrayList<String> banned = movies.getCountriesBanned();

            int checkIfBanned = 1;

            for (int i = 0; i < banned.size(); i++) {
                if (banned.get(i).equals(country)) {
                    checkIfBanned = 0;
                }
            }
            if (checkIfBanned == 1) {
                movieClass aux = new movieClass(movies, 1);
                allowedMovies.add(aux);
            }
        }
        // get the movie that matches the title
        ArrayList<movieClass> matchingTitle = new ArrayList<>();
        ArrayList<movieClass> matching = new ArrayList<>();
        movieClass matchingAux = new movieClass();
        String movieName = actionsArr.getMovie();
        int ok = 0;
        for (movieClass movies : allowedMovies) {
            if (movies.getName().equals(movieName)) {
                 matchingAux = new movieClass(movies, 1);
                 matchingTitle.add(matchingAux);
                 ok = 1;
            }
        }
        if (ok == 0) {
            // if there is no matching movie
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore());
            node.putPOJO("currentUser", null);
            output.add(node);
        } else {
            StaticValues.setPageName("seeDetails");
            StaticValues.setCurrentMovie(matchingTitle.get(0).getName());
            UserClass newUser = new UserClass(StaticValues.getCurrentUser());
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", (JsonNode) null);
            node.putPOJO("currentMoviesList", matchingTitle.toArray());
            node.putPOJO("currentUser", newUser);
            output.add(node);
        }
    }
    /** function to see details for a movie after a filter*/

    public void seeDetailsAfterFilters(final Database dataBase,
                                       final ArrayNode output,
                                       final actionsClass actionsArr) {

        // search the movie in the fltered ones
        ObjectMapper objectMapper = new ObjectMapper();
        String movieName = actionsArr.getMovie();
        int ok = 0;
        ArrayList<movieClass> movieAux = new ArrayList<>();
        movieClass movieNew = null;
        for (movieClass movies : StaticValues.getFilteredMovies()) {
            if (movies.getName().equals(movieName)) {
                ok = 1;
                movieNew = new movieClass(movies, 1);
                movieAux.add(movieNew);
            }
        }
        if (ok == 0) {
            // if there is no match
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore().toArray());
            node.putPOJO("currentUser", null);
            output.add(node);
            StaticValues.setPageName("moviesPage");
            StaticValues.setFilteredMovies(null);
        } else {
            UserClass newUser = new UserClass(StaticValues.getCurrentUser());
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", (JsonNode) null);
            node.putPOJO("currentMoviesList", movieAux.toArray());
            node.putPOJO("currentUser", newUser);
            output.add(node);
        }
    }

}
