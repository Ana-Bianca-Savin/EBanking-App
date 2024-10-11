package org.poo.cb;

import java.util.ArrayList;

public class AddAccountCommand extends Command {
    private final String email;
    private final Currency currency;

    public AddAccountCommand(String email, Currency currency) {
        this.email = email;
        this.currency = currency;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Check if an account in this currency already exists
        if (user.getPortfolio().getAccounts().containsKey(this.currency)) {
            System.out.println("Account in currency " + this.currency + " already exists for user");
        } else {
            //  Create the account
            user.getPortfolio().addAccount(new ProxyAccount(this.currency));
        }
    }
}
