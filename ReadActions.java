import input.actionsClass;
import pages.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pages.Database;
import java.util.ArrayList;

public final class ReadActions {


    public ReadActions() {
    }

    /** function to read the given commands and execute*/
    public void read(final ArrayNode output, final inputClass inputData) {

        ObjectMapper objectMapper = new ObjectMapper();

        HomePageUnauthenticated homePageUnauthenticated =
                HomePageUnauthenticated.getInstance();
        HomePageAuthenticated homePageAuthenticated =
                HomePageAuthenticated.getInstance();
        LoginPage loginPage = LoginPage.getInstance();
        RegisterPage registerPage = RegisterPage.getInstance();
        MoviesPage moviesPage = new MoviesPage();
        SeeDetails seeDetails = new SeeDetails();
        UpgradesPage upgradesPage = new UpgradesPage();

        Database dataBase = Database.getInstance();
        dataBase.setMovies(inputData.getMovies());
        dataBase.setRegistredUsers(inputData.getUsers());

        // current page is homePageUnauthenticated
        StaticValues.setPageName("homePageUnauthenticated");
        StaticValues.setCurrentUser(null);
        StaticValues.setCurrentMovie(null);

        ArrayList<actionsClass> actions = new ArrayList<>();
        actions = inputData.getActions();

        for (actionsClass actionsArr : actions)  {
            if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("login")) {
                if (StaticValues.getPageName().equals(
                        "homePageUnauthenticated")) {
                    StaticValues.setPageName("loginPage");
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("register")) {
                if (StaticValues.getPageName().
                        equals("homePageUnauthenticated")) {
                    StaticValues.setPageName("registerPage");
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }

            } else if (actionsArr.getType().equals("on page")
                    && actionsArr.getFeature().equals("register")) {
                if (StaticValues.getPageName().equals("registerPage")) {
                    registerPage.register(actionsArr, dataBase, output);

                }

            } else if (actionsArr.getType().equals("on page")
                    && actionsArr.getFeature().equals("login")) {
                if (StaticValues.getPageName().equals("loginPage")) {
                    loginPage.login(actionsArr, dataBase, output);
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("logout")) {
                if (StaticValues.getPageName().
                        equals("homePageAuthenticated")) {
                    homePageAuthenticated.logout();
                } else if (StaticValues.getPageName().equals("moviesPage")) {
                    homePageAuthenticated.logout();
                } else if (StaticValues.getPageName().equals("filters")) {
                    homePageAuthenticated.logout();
                } else if (StaticValues.getPageName().equals("seeDetails")) {
                    homePageAuthenticated.logout();
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("movies")) {
                if (StaticValues.getPageName().equals("homePageAuthenticated")
                        || StaticValues.getPageName().equals("moviesPage")
                        || StaticValues.getPageName().equals("upgradesPage")
                        || StaticValues.getPageName().equals("seeDetails")
                        || StaticValues.getPageName().equals("filters")) {
                    StaticValues.setPageName("moviesPage");
                    moviesPage.showMovies(dataBase, output);
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("on page")
                    && (actionsArr.getFeature().equals("filter")
                    || actionsArr.getFeature().equals("search"))) {
                if (StaticValues.getPageName().equals("moviesPage")) {
                    if (actionsArr.getFeature().equals("search")) {
                        moviesPage.search(dataBase, output, actionsArr);
                    } else if (actionsArr.getFeature().equals("filter")) {
                        moviesPage.filter(dataBase, output, actionsArr);
                    }

                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            }  else if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("see details")) {
                if (StaticValues.getPageName().equals("moviesPage")) {
                    moviesPage.details(dataBase, output, actionsArr);
                } else if (StaticValues.getPageName().equals("filters")) {
                    moviesPage.seeDetailsAfterFilters(dataBase,
                            output, actionsArr);
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("on page")
                    && (actionsArr.getFeature().equals("like")
                    || actionsArr.getFeature().equals("watch")
                    || actionsArr.getFeature().equals("rate")
                    || actionsArr.getFeature().equals("purchase"))) {
                if (StaticValues.getPageName().equals("seeDetails")) {
                    if (actionsArr.getFeature().equals("watch")) {
                        seeDetails.watch(dataBase, output, actionsArr);
                    } else if (actionsArr.getFeature().equals("purchase")) {
                        seeDetails.purchase(dataBase, output, actionsArr);
                    } else if (actionsArr.getFeature().equals("like")) {
                        seeDetails.like(dataBase, output, actionsArr);
                    } else if (actionsArr.getFeature().equals("rate")) {
                        seeDetails.rate(dataBase, output, actionsArr);
                    }
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("change page")
                    && actionsArr.getPage().equals("upgrades")) {
                if (StaticValues.getPageName().
                        equals("homePageAuthenticated")) {
                    StaticValues.setPageName("upgradesPage");
                } else {
                    ObjectNode node = objectMapper.createObjectNode();
                    node.put("error", "Error");
                    node.putPOJO("currentMoviesList",
                            dataBase.getMoviesBefore().toArray());
                    node.putPOJO("currentUser", null);
                    output.add(node);
                }
            } else if (actionsArr.getType().equals("on page")
                    && StaticValues.getPageName().equals("upgradesPage")) {
                if (actionsArr.getFeature().equals("buy tokens")) {
                    upgradesPage.buyTokens(output, actionsArr, dataBase);
                } else if (actionsArr.getFeature().
                        equals("buy premium account")) {
                    upgradesPage.buyPremium(output, actionsArr, dataBase);
                }
            }
        }

    }

}
