import input.UserClass;
import input.actionsClass;
import input.movieClass;

import java.util.ArrayList;

public final class inputClass {

    private ArrayList<UserClass> users;
    private ArrayList<movieClass> movies;
    private ArrayList<actionsClass> actions;

    public inputClass() {
    }

    public inputClass(final ArrayList<UserClass> users,
                      final ArrayList<movieClass> movies,
                      final ArrayList<actionsClass> actions) {
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }

    public ArrayList<UserClass> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<UserClass> users) {
        this.users = users;
    }

    public ArrayList<movieClass> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<movieClass> movies) {
        this.movies = movies;
    }

    public ArrayList<actionsClass> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<actionsClass> actions) {
        this.actions = actions;
    }
}
