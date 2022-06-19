package client.utility;

import common.User;

import java.util.Scanner;

public class UserWrapper {

    private static User user = null;

    public User getUser(Scanner scanner) {
        if (this.user == null) {
            Console console = Console.getInstance();
            this.user = new User(console.getString(scanner, "username"), console.getString(scanner, "password"));
        }
        return this.user;
    }

}
