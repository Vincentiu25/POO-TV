package pages;
import input.UserClass;
import input.actionsClass;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class UpgradesPage extends Page {
    public UpgradesPage() {
    }
    /** function to buy premium account*/
    public void buyPremium(final ArrayNode output, final actionsClass
            actionsArr, final Database dataBase) {
        UserClass currentUser = StaticValues.getCurrentUser();
        for (UserClass userArr : dataBase.getRegistredUsers()) {
            if (userArr.getCredentials().getName().equals(currentUser.
                    getCredentials().getName())) {
                if (userArr.getCredentials().getPassword().equals(currentUser.
                        getCredentials().getPassword())) {
                    userArr.getCredentials().setAccountType("premium");

                    userArr.setTokensCount(userArr.getTokensCount() - 10);
                }
            }
        }
    }
    /** function to buy tokens*/
    public void buyTokens(final ArrayNode output, final actionsClass
            actionsArr, final Database dataBase) {
        UserClass currentUser = StaticValues.getCurrentUser();
        for (UserClass userArr : dataBase.getRegistredUsers()) {
            if (userArr.getCredentials().getName().equals(currentUser.
                    getCredentials().getName())) {
                if (userArr.getCredentials().getPassword().equals(currentUser.
                        getCredentials().getPassword())) {
                   int nrTokenBuy = actionsArr.getCount();

                    String balance = userArr.getCredentials().getBalance();
                    int balanceInt = Integer.parseInt(balance);
                    balanceInt -= nrTokenBuy;
                    String balanceFinal = String.valueOf(balanceInt);
                    userArr.getCredentials().setBalance(balanceFinal);

                    userArr.setTokensCount(userArr.getTokensCount()
                            + nrTokenBuy);
                }
            }
        }
    }
}
