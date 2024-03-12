package pages;

public final class HomePageUnauthenticated extends Page {
    private static HomePageUnauthenticated instance = null;
    private HomePageUnauthenticated() {
    }
    /** Singleton declaration*/
    public static HomePageUnauthenticated getInstance() {
        if (instance == null) {
            instance = new HomePageUnauthenticated();
        }
        return instance;
    }

}
