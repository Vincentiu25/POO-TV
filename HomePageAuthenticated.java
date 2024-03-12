package pages;

public final class HomePageAuthenticated extends Page {

    private static HomePageAuthenticated instance = null;
    private HomePageAuthenticated() {
    }
    /** Singleton declaration*/
    public static HomePageAuthenticated getInstance() {
        if (instance == null) {
            instance = new HomePageAuthenticated();
        }
        return instance;
    }

    /** function to log out*/
    public void logout() {
        StaticValues.setPageName("homePageUnauthenticated");
        StaticValues.setCurrentUser(null);
    }

}
