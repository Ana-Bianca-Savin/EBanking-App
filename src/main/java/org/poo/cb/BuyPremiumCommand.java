package org.poo.cb;

public class BuyPremiumCommand extends Command {
    private final String email;

    public BuyPremiumCommand(String email) {
        this.email = email;
    }

    public void execute(Bank bank) {
        //  Get the user
        User user = bank.getUsers().get(this.email);

        //  Check if the user exists
        if (user == null) {
            System.out.println("User with " + this.email + " doesn't exist");
            return;
        }

        //  Try to extract the money
        boolean extractedSuccessfully = user.getPortfolio().getAccounts().get(Currency.USD).extract(100);
        if (!extractedSuccessfully)
            System.out.println("Insufficient amount in account for buying premium option");
        else
            user.setHasPremium(true);
    }
}
