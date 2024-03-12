package pages;

import input.UserClass;
import input.actionsClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class RegisterPage extends Page {

    private static RegisterPage instance = null;
    private RegisterPage() {
    }
    /** Singleton declaration*/
    public static RegisterPage getInstance() {
        if (instance == null) {
            instance = new RegisterPage();
        }
        return instance;
    }

    /** function to register an user*/
    public void register(final actionsClass actionsArr,
                          final Database dataBase,
                          final ArrayNode output) {

        ObjectMapper objectMapper = new ObjectMapper();

        UserClass newUser = new UserClass(actionsArr.getCredentials());
        newUser.setNumFreePremiumMovies(15);
        dataBase.getRegistredUsers().add(newUser);
        StaticValues.setCurrentUser(newUser);

        CheckErrors check = new CheckErrors();
        int registerCheck = check.checkRegister(dataBase,
                actionsArr.getCredentials());
        if (registerCheck == -1) {
            // the user is already registered
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore().toArray());
            node.putPOJO("currentUser",
                    StaticValues.getCurrentUser());
            output.add(node);
            StaticValues.setPageName("homePageUnauthenticated");

        } else  {
            StaticValues.setCurrentUser(dataBase.getRegistredUsers().
                    get(registerCheck));

            UserClass newUserAux = new UserClass(StaticValues.
                    getCurrentUser());
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", (JsonNode) null);
            node.putPOJO("currentMoviesList",
                    dataBase.getMoviesBefore().toArray());
            node.putPOJO("currentUser", newUserAux);
            output.add(node);

            StaticValues.setPageName("homePageAuthenticated");
        }

    }
}
