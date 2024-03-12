package pages;

import input.UserClass;
import input.actionsClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class LoginPage extends Page {

    private static LoginPage instance = null;
    private LoginPage() {
    }
    /** Singleton declaration*/
    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();
        }
        return instance;
    }

    /** function to log in an user*/
    public void login(final actionsClass actionsArr,
                      final Database dataBase, final ArrayNode output) {

        ObjectMapper objectMapper = new ObjectMapper();
        CheckErrors check = new CheckErrors();
        int loginCheck = check.checkLogin(dataBase, actionsArr.getCredentials());
        if (loginCheck == -1) {
            // if the user is not registered
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", "Error");
            node.putPOJO("currentMoviesList", dataBase.getMoviesBefore().toArray());
            node.put("currentUser", (JsonNode) null);
            output.add(node);
            StaticValues.setPageName("homePageUnauthenticated");

        } else  {
            // if the user is registered
            StaticValues.setCurrentUser(dataBase.getRegistredUsers().get(loginCheck));

            UserClass newUser = new UserClass(StaticValues.getCurrentUser());
            ObjectNode node = objectMapper.createObjectNode();
            node.put("error", (JsonNode) null);
            node.putPOJO("currentMoviesList", dataBase.getMoviesBefore().toArray());
            node.putPOJO("currentUser", newUser);
            output.add(node);
            // go to the HomeAuthenticatedPage
            StaticValues.setPageName("homePageAuthenticated");
        }
    }

}
