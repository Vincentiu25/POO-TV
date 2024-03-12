package pages;

import input.filtersClass;
import input.movieClass;

import java.util.ArrayList;

public final class Filters {
    public Filters() {
    }

    /** function to apply filters*/
    public ArrayList filter(final filtersClass filters,
                            final ArrayList<movieClass> allowedMovies) {

        StaticValues.setPageName("filters");

        ArrayList<String> actors = new ArrayList<>();
        ArrayList<String> genre = new ArrayList<>();
        // check what filters need to be applied
        if (filters.getContains() != null) {
            if (filters.getContains().getActors() != null) {
                actors = filters.getContains().getActors();
            } else {
                actors = null;
            }
            if (filters.getContains().getGenre() != null) {
                genre = filters.getContains().getGenre();
            } else {
                genre = null;
            }
        }

        String duration = null;
        String rating = null;
        if (filters.getSort() != null) {
             duration = filters.getSort().getDuration();
             rating = filters.getSort().getRating();
        }

        if (duration != null) {
            // sort by duration
            int n = allowedMovies.size();
            if (duration.equals("decreasing")) {
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (allowedMovies.get(j).getDuration()
                                < allowedMovies.get(j + 1).getDuration()) {
                            movieClass temp1 = allowedMovies.get(j);
                            movieClass temp2 = allowedMovies.get(j + 1);
                            allowedMovies.set(j, temp2);
                            allowedMovies.set(j + 1, temp1);
                        } else if (allowedMovies.get(j).getDuration()
                                == allowedMovies.get(j + 1).getDuration()) {
                            // if the duration is the samee, sort by rating
                            if (rating.equals("decreasing")) {
                                if (allowedMovies.get(j).getRating()
                                        < allowedMovies.get(j + 1).getRating()) {
                                    movieClass temp1 = allowedMovies.get(j);
                                    movieClass temp2 = allowedMovies.get(j + 1);
                                    allowedMovies.set(j, temp2);
                                    allowedMovies.set(j + 1, temp1);
                                }
                            } else {
                                if (allowedMovies.get(j).getRating()
                                        > allowedMovies.get(j + 1).getRating()) {
                                    movieClass temp1 = allowedMovies.get(j);
                                    movieClass temp2 = allowedMovies.get(j + 1);
                                    allowedMovies.set(j, temp2);
                                    allowedMovies.set(j + 1, temp1);
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (allowedMovies.get(j).getDuration()
                                > allowedMovies.get(j + 1).getDuration()) {
                            movieClass temp1 = allowedMovies.get(j);
                            movieClass temp2 = allowedMovies.get(j + 1);
                            allowedMovies.set(j, temp2);
                            allowedMovies.set(j + 1, temp1);
                        } else if (allowedMovies.get(j).getDuration()
                                == allowedMovies.get(j + 1).getDuration()) {
                            if (rating.equals("decreasing")) {
                                if (allowedMovies.get(j).getRating()
                                        < allowedMovies.get(j + 1).getRating()) {
                                    movieClass temp1 = allowedMovies.get(j);
                                    movieClass temp2 = allowedMovies.get(j + 1);
                                    allowedMovies.set(j, temp2);
                                    allowedMovies.set(j + 1, temp1);
                                }
                            } else {
                                if (allowedMovies.get(j).getRating()
                                        > allowedMovies.get(j + 1).getRating()) {
                                    movieClass temp1 = allowedMovies.get(j);
                                    movieClass temp2 = allowedMovies.get(j + 1);
                                    allowedMovies.set(j, temp2);
                                    allowedMovies.set(j + 1, temp1);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (duration == null && rating != null) {
            // if sort only by rating
            int n = allowedMovies.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (rating.equals("decreasing")) {
                        if (allowedMovies.get(j).getRating()
                                < allowedMovies.get(j + 1).getRating()) {
                            movieClass temp1 = allowedMovies.get(j);
                            movieClass temp2 = allowedMovies.get(j + 1);
                            allowedMovies.set(j, temp2);
                            allowedMovies.set(j + 1, temp1);
                        }
                    } else {
                        if (allowedMovies.get(j).getRating()
                                > allowedMovies.get(j + 1).getRating()) {
                            movieClass temp1 = allowedMovies.get(j);
                            movieClass temp2 = allowedMovies.get(j + 1);
                            allowedMovies.set(j, temp2);
                            allowedMovies.set(j + 1, temp1);
                        }
                    }
                }
            }
        }

        // crerate a new array with only the matching movies
        ArrayList<movieClass> filteredActors = new ArrayList<>();
        if (actors != null && genre == null) {
            // filter only by actors
            for (int i = 0; i < allowedMovies.size(); i++) {
                int ok = 0;

                for (int j = 0; j < actors.size(); j++) {
                    if (allowedMovies.get(i).getActors().
                            contains(actors.get(j))) {
                        ok = 0;
                    } else {
                        ok = 1;
                    }
                }
                if (ok == 0) {
                    filteredActors.add(allowedMovies.get(i));
                }
            }
        }
        if (genre != null && actors == null) {
            // filter only by genre
            for (int i = 0; i < allowedMovies.size(); i++) {
                int ok = 0;
                for (int j = 0; j < genre.size(); j++) {
                    if (allowedMovies.get(i).getGenres().
                            contains(genre.get(j))) {
                        ok = 0;
                    } else {
                        ok = 1;
                    }
                }

                if (ok == 0) {
                    filteredActors.add(allowedMovies.get(i));
                }
            }
        }

        if (genre != null && actors != null) {
            //filter by both actors and genre
            for (int i = 0; i < allowedMovies.size(); i++) {
                int okActors = 0;
                int okGenre = 0;
                for (int j = 0; j < genre.size(); j++) {
                    if (allowedMovies.get(i).getGenres().
                            contains(genre.get(j))) {
                        okGenre = 0;
                    } else {
                        okGenre = 1;
                    }

                }
                for (int j = 0; j < actors.size(); j++) {
                    if (allowedMovies.get(i).getActors().
                            contains(actors.get(j))) {
                        okActors = 0;
                    } else {
                        okActors = 1;
                    }
                }
                if (okActors == 0 && okGenre == 0) {
                    filteredActors.add(allowedMovies.get(i));
                }
            }
        }
        // keep the filtered movies for next search
        StaticValues.setFilteredMovies(filteredActors);
        //return the new array
        return filteredActors;
    }
}
