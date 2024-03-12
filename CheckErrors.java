package pages;

import input.UserClass;
import input.credentials;

public final class CheckErrors {

    public CheckErrors() {
    }

    /** function to search for an existing user*/
    public int checkLogin(final Database dataBase,
                          final credentials credentials) {
        String name = credentials.getName();
        String password = credentials.getPassword();

        int userNr = 0;
        if (dataBase.getRegistredUsers() == null) {
            return -1;
        }
        for (UserClass userArr : dataBase.getRegistredUsers()) {
            // if there is a user with those credentials registered
            // return 1, else return 0
            if (userArr.getCredentials().getName().equals(name)
                    && userArr.getCredentials().getPassword().
                    equals(password)) {
                return userNr;
            }
            userNr++;
        }
        return -1;
    }
    /** function to search for an already registered user*/
    public int checkRegister(final Database dataBase,
                             final credentials credentials) {
        String name = credentials.getName();
        String password = credentials.getPassword();

        int userNr = 0;
        if (dataBase.getRegistredUsers() == null) {
            return -1;
        }
        for (UserClass userArr : dataBase.getRegistredUsers()) {
            // if there is a user with those credentials already registered
            // return 1, else return 0
            if (userArr.getCredentials().getName().equals(name)
                    && userArr.getCredentials().getPassword().
                    equals(password)) {
                return userNr;
            }
            userNr++;
        }
        return -1;
    }

}
