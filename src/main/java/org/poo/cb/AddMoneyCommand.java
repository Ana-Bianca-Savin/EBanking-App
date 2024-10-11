package org.poo.cb;

import java.util.ArrayList;

public class AddMoneyCommand extends Command {
    final private String email;
    final private Currency currency;
    final private double amount;

    public AddMoneyCommand(String email, Currency currency, double amount) {
        this.email = email;
        this.currency = currency;
        this.amount = amount;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Get account with the right currency from portfolio and add money if possible
        Account acc = user.getPortfolio().getAccounts().get(this.currency);

        boolean addedFundsSuccessfully = acc.add(this.amount);
        if (!addedFundsSuccessfully)
            System.out.println("You can't add a negative amount of money");
    }
}
