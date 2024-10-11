package org.poo.cb;

import java.util.ArrayList;

public class TransferMoneyCommand extends Command {
    final private String email;
    final private String friendEmail;
    final private Currency currency;
    final private double amount;

    public TransferMoneyCommand(String email, String friendEmail, Currency currency, double amount) {
        this.email = email;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.amount = amount;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);
        User friend = bank.getUsers().get(this.friendEmail);

        //  Check if both users exist
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }
        if (friend == null) {
            System.out.println("User with " + this.friendEmail + " doesn't exist");
            return;
        }

        //  Check if the users are friends
        ArrayList<User> friends = user.getFriends();
        boolean found = false;
        for (User u: friends)
            if (u.getEmail().equals(this.friendEmail)) {
                found = true;
                break;
            }
        if (!found) {
            System.out.println("You are not allowed to transfer money to" + this.friendEmail);
            return;
        }

        //  Get the accounts
        Account accSource = user.getPortfolio().getAccounts().get(this.currency);
        Account accDest = friend.getPortfolio().getAccounts().get(this.currency);

        //  Try to extract the amount
        boolean extractSuccessfully = accSource.extract(this.amount);
        if (!extractSuccessfully) {
            System.out.println("Insufficient amount in account " + this.currency + " for transfer");
            return;
        }

        boolean addedSuccessfully = accDest.add(this.amount);
        if (!addedSuccessfully)
            System.out.println("You can't add a negative amount of money");
    }
}
