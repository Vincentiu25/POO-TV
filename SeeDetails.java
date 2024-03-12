import input.UserClass;
import input.actionsClass;
import input.movieClass;
import pages.Database;
import pages.StaticValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public final class SeeDetails {

    public SeeDetails() {
    }
    /** function to watch a movie*/
    public void watch(final Database dataBase, final ArrayNode output,
                      final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();
        movieClass movieAux = new movieClass();
        ArrayList<movieClass> movieToWatch = new ArrayList<>();
        movieClass movieToAdd = null;
        for (movieClass movies : dataBase.getMovies()) {
            // get the movie from the database
            if (movies.getName().equals(StaticValues.getCurrentMovie())) {
                movieToAdd = new movieClass(movies, 1);
                movieToWatch.add(movieToAdd);
                movieAux = movies;
            }
        }

        int ok = 0;
        for (movieClass moviesArr : StaticValues.getCurrentUser().
                getPurchasedMovies()) {
            // check if the movie is purchased
            if (moviesArr.getName().equals(StaticValues.
                    getCurrentMovie())) {
                ok = 1;
            }
        }
        if (ok == 0) {
            // if the movie is not purchased
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore());
            node.putPOJO("currentUser", null);
            output.add(node);
            return;
        }
        StaticValues.getCurrentUser().getWatchedMovies().add(movieAux);

        UserClass newUser = new UserClass(StaticValues.getCurrentUser());
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList", movieToWatch);
        node.putPOJO("currentUser", newUser);
        output.add(node);
    }

    /** function to purchase a movie*/
    public void purchase(final Database dataBase, final ArrayNode output,
                         final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();

        movieClass movieAux = new movieClass();
        ArrayList<movieClass> movieToWatch = new ArrayList<>();
        movieClass movieToAdd = null;
        for (movieClass movies : dataBase.getMovies()) {
            // search for the movie
            if (movies.getName().equals(StaticValues.getCurrentMovie())) {
                movieToAdd = new movieClass(movies, 1);
                movieToWatch.add(movieToAdd);
                movieAux = movies;
            }
        }
        StaticValues.getCurrentUser().getPurchasedMovies().add(movieAux);
        // pay for the movie depending on the account type
        if (StaticValues.getCurrentUser().getCredentials().getAccountType().
                equals("premium")) {
            if (StaticValues.getCurrentUser().getNumFreePremiumMovies() > 0) {
                int nrMovies = StaticValues.getCurrentUser().
                        getNumFreePremiumMovies();
                StaticValues.getCurrentUser().
                        setNumFreePremiumMovies(nrMovies - 1);
            } else {
                int nrTokens = StaticValues.getCurrentUser().getTokensCount();
                StaticValues.getCurrentUser().setTokensCount(nrTokens - 2);
            }

        } else {
            int nrTokens = StaticValues.getCurrentUser().getTokensCount();
            StaticValues.getCurrentUser().setTokensCount(nrTokens - 2);
        }
        UserClass newUser = new UserClass(StaticValues.getCurrentUser());
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList", movieToWatch);
        node.putPOJO("currentUser", newUser);
        output.add(node);
    }
    /** function to like a movie*/

    public void like(final Database dataBase,
                     final ArrayNode output,
                     final actionsClass actionsArr) {
        ObjectMapper objectMapper = new ObjectMapper();
        movieClass movieAux = new movieClass();
        ArrayList<movieClass> movieToWatch = new ArrayList<>();
        movieClass movieToAdd = null;
        int ok = 0;
        for (movieClass movies : StaticValues.getCurrentUser().
                getWatchedMovies()) {
            // check if the movie has been watched before
            if (movies.getName().equals(StaticValues.getCurrentMovie())) {
                ok = 1;
                movieAux = movies;
                movieAux.setNumLikes(movieAux.getNumLikes() + 1);
                movieToAdd = new movieClass(movies, 1);
                movieToWatch.add(movieToAdd);
            }
        }
        if (ok == 0) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore());
            node.putPOJO("currentUser", null);
            output.add(node);
            return;
        }
        StaticValues.getCurrentUser().getLikedMovies().add(movieAux);

        UserClass newUser = new UserClass(StaticValues.getCurrentUser());
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList", movieToWatch);
        node.putPOJO("currentUser", newUser);
        output.add(node);
    }

    /** function to rate a movie*/
    public void rate(final Database dataBase, final ArrayNode output,
                     final actionsClass actionsArr) {

        ObjectMapper objectMapper = new ObjectMapper();
        movieClass movieAux = new movieClass();
        ArrayList<movieClass> movieToWatch = new ArrayList<>();
        movieClass movieToAdd = null;
        int ok = 0;
        if (actionsArr.getRate() > 5) {
            // check if the rating id bigger than 5
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore());
            node.putPOJO("currentUser", null);
            output.add(node);
            return;
        }
        for (movieClass movies : StaticValues.getCurrentUser().
                getWatchedMovies()) {
            if (movies.getName().equals(StaticValues.getCurrentMovie())) {
                // if the movie has been watched , rate it
                ok = 1;
                movieAux = movies;
                movieAux.setSumOfRatings(movieAux.getSumOfRatings()
                        + actionsArr.getRate());
                movieAux.setNumRatings(movieAux.getNumRatings() + 1);
                movieAux.setRating(movieAux.getSumOfRatings()
                        / movieAux.getNumRatings());
                movieToAdd = new movieClass(movies, 1);
                movieToWatch.add(movieToAdd);
            }
        }

        if (ok == 0) {
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore());
            node.putPOJO("currentUser", null);
            output.add(node);
            return;
        }
        StaticValues.getCurrentUser().getRatedMovies().add(movieAux);
        UserClass newUser = new UserClass(StaticValues.getCurrentUser());
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList", movieToWatch);
        node.putPOJO("currentUser", newUser);
        output.add(node);




    }
}
